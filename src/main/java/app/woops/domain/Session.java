package app.woops.domain;

import java.math.BigInteger;
import java.security.SecureRandom;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Session {
    private static final SecureRandom RANDOM = new SecureRandom();

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private String sessionKey;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    private Session() {}

    public Session(User user) {
        this.user = user;
        this.sessionKey = generateSessionKey();
    }

    private String generateSessionKey() {
        return new BigInteger(130, RANDOM).toString(32);
    }

    public Long getId() {
        return id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public User getUser() {
        return user;
    }
}
