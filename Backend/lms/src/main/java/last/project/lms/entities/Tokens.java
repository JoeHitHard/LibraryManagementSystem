package last.project.lms.entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tokens")
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String token;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    public Tokens(long userId, String token, LocalDateTime expirationTime) {
        this.userId = userId;
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public Tokens() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
