package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.util.Set;

// Table is named quiz_user (not "user") because USER is a reserved word in MySQL
@Entity
@Table(name = "quiz_user")
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private int id;

    @Column(name = "qu_username")
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<Attempt> attempts;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Set<Attempt> getAttempts() { return attempts; }
    public void setAttempts(Set<Attempt> attempts) { this.attempts = attempts; }

    @Override
    public String toString() {
        return "QuizUser{id=" + id + ", username='" + username + "'}";
    }
}
