package com.example.securityfundamentals.accont;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Entity
@Table(name = "new_verification_token")
public class VerificationToken {
    public static final int EXPIRATION_MINUTES = 5;

    @Id
    private String username;
    private String token;
    private LocalDateTime expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime calculateExpiry() {
        return LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
    }
}
