package last.project.lms.entities;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    private String name;
    private String author;
    private long totalBooks;
    private long availableBooks;
}
