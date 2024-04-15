import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Dashboard.css';

const Dashboard = () => {
  const [books, setBooks] = useState([]);
  const [newBook, setNewBook] = useState({
    name: '',
    author: '',
    totalBooks: 0,
    availableBooks: 0
  });
  const [editingBookId, setEditingBookId] = useState(null);

  const fetchData = async () => {
    try {
      const booksResponse = await axios.get('http://localhost:8082/api/book', {
        headers: {
          'Authorization': localStorage.getItem('jwtToken'),
          'Content-Type': 'application/json'
        }
      });
      setBooks(booksResponse.data);

      const booksLentResponse = await axios.get('http://localhost:8083/api/lend', {
        headers: {
          'Authorization': localStorage.getItem('jwtToken'),
          'Content-Type': 'application/json'
        }
      });
      setBooksLent(booksLentResponse.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleAddBook = async () => {
    try {
      await axios.post('http://localhost:8082/api/book', newBook, {
        headers: {
          'Authorization': localStorage.getItem('jwtToken'),
          'Content-Type': 'application/json'
        }
      });
      setNewBook({
        name: '',
        author: '',
        totalBooks: 0,
        availableBooks: 0
      });
      setShowAddBookForm(false); // Hide the form after adding book
      fetchData();
    } catch (error) {
      console.error('Error adding book:', error);
    }
  };

  const handleEditBook = async () => {
    try {
      await axios.put(`http://localhost:8082/api/book/${editingBookId}`, newBook, {
        headers: {
          'Authorization': localStorage.getItem('jwtToken'),
          'Content-Type': 'application/json'
        }
      });
      setEditingBookId(null);
      setNewBook({
        name: '',
        author: '',
        totalBooks: 0,
        availableBooks: 0
      });
      fetchData();
    } catch (error) {
      console.error('Error editing book:', error);
    }
  };

  const handleDeleteBook = async (bookId) => {
    try {
      await axios.delete(`http://localhost:8082/api/book/${bookId}`, {
        headers: {
          'Authorization': localStorage.getItem('jwtToken')
        }
      });
      fetchData();
    } catch (error) {
      console.error('Error deleting book:', error);
    }
  };

  return (
    <div className='dashboard-container'>
      <h1>
        Library Management System
      </h1>
      <button onClick={() => setShowAddBookForm(!showAddBookForm)}>Add Book</button> {/* Toggle form visibility */}
        {showAddBookForm && ( {/* Conditional rendering of the form */}
          <div className='form'>
            <input type="text" value={newBook.name} onChange={(e) => setNewBook({ ...newBook, name: e.target.value })} placeholder="Name" />
            <input type="text" value={newBook.author} onChange={(e) => setNewBook({ ...newBook, author: e.target.value })} placeholder="Author" />
            <input type="number" value={newBook.totalBooks} onChange={(e) => setNewBook({ ...newBook, totalBooks: e.target.value })} placeholder="Total Books" />
            <input type="number" value={newBook.availableBooks} onChange={(e) => setNewBook({ ...newBook, availableBooks: e.target.value })} placeholder="Available Books" />
            {editingBookId ? (
              <button onClick={handleEditBook}>Save</button>
            ) : (
              <button onClick={handleAddBook}>Add Book</button>
            )}
          </div>
        )}
      <div className='section'>
        <h2>
          Available Books
        </h2>
        <table className="table">
          <thead>
            <tr>
              <th>Book ID</th>
              <th>Name</th>
              <th>Author</th>
              <th>Total Books</th>
              <th>Available Books</th>
              <th>Edit</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {books.map(book => (
              <tr key={book.bookId}>
                <td>{book.bookId}</td>
                <td>{book.name}</td>
                <td>{book.author}</td>
                <td>{book.totalBooks}</td>
                <td>{book.availableBooks}</td>
                <td><button onClick={() => setEditingBookId(book.bookId)}>Edit</button></td>
                <td><button onClick={() => handleDeleteBook(book.bookId)}>Delete</button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Dashboard;
