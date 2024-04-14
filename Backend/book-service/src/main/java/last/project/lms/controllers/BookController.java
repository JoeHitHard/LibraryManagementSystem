package last.project.lms.controllers;

import last.project.lms.entities.Books;
import last.project.lms.entities.Users;
import last.project.lms.exceptions.InvalidAuthException;
import last.project.lms.repo.BookRepo;
import last.project.lms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    AuthService authService;

    @GetMapping("/{bookId}")
    public ResponseEntity getBook(@PathVariable("bookId") String bookId, @RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        return ResponseEntity.ok(bookRepo.findById(Long.valueOf(bookId)));
    }

    @GetMapping("")
    public ResponseEntity getAllBooks() throws InvalidAuthException {
        return ResponseEntity.ok(bookRepo.findAll());
    }

    @PostMapping("")
    public ResponseEntity addBook(@RequestBody Books book, @RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        Optional<Users> user = authService.getUser(authorizationHeader);
        if (user.isPresent()) {
            if (user.get().getRole().equals("admin")) {
                bookRepo.save(book);
                bookRepo.flush();
            }
        }
        throw new InvalidAuthException("Invalid Auth");
    }

    @PutMapping("")
    public ResponseEntity updateBook(@RequestBody Books book, @RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        Optional<Users> user = authService.getUser(authorizationHeader);
        if (user.isPresent()) {
            if (user.get().getRole().equals("admin")) {
                bookRepo.save(book);
                bookRepo.flush();
            }
        }
        throw new InvalidAuthException("Invalid Auth");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("bookId") String bookId, @RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        Optional<Users> user = authService.getUser(authorizationHeader);
        if (user.isPresent()) {
            if (user.get().getRole().equals("admin")) {
                bookRepo.deleteById(Long.valueOf(bookId));
                bookRepo.flush();
            }
        }
        throw new InvalidAuthException("Invalid Auth");
    }

    @ExceptionHandler(InvalidAuthException.class)
    public ResponseEntity<Object> handleInvalidAuthException(InvalidAuthException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
