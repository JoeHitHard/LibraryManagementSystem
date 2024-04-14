package last.project.lms.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "lent")
public class BooksLent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lentId;

    private String userId;
    private String bookId;
}
