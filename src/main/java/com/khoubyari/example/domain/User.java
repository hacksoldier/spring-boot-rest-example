package com.khoubyari.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(name = "UC_USERS_EMAIL", columnNames = "EMAIL"),
        @UniqueConstraint(name = "UC_USERS_USERNAME", columnNames = "USERNAME")}
)
public class User {
    private static final String SEQ_NAME = "SEQ_USER";

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = SEQ_NAME, initialValue = 50000)
    @GeneratedValue(generator = SEQ_NAME, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", length = 64)
    private String password;

    @Lob
    @Column(name = "LAST_PASSWORDS")
    private String lastPassowrds;

    @Column(name = "CREATED", updatable = false)
    private LocalDateTime created;

    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastPassowrds() {
        return lastPassowrds;
    }

    public void setLastPassowrds(String lastPassowrds) {
        this.lastPassowrds = lastPassowrds;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastPassowrds='" + lastPassowrds + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
