package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "attempt")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "at_qu_id")
    private QuizUser user;

    @Column(name = "at_date")
    private LocalDate attemptDate;

    @OneToMany(mappedBy = "attempt")
    private Set<AttemptQuestion> attemptQuestions;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public QuizUser getUser() { return user; }
    public void setUser(QuizUser user) { this.user = user; }

    public LocalDate getAttemptDate() { return attemptDate; }
    public void setAttemptDate(LocalDate attemptDate) { this.attemptDate = attemptDate; }

    public Set<AttemptQuestion> getAttemptQuestions() { return attemptQuestions; }
    public void setAttemptQuestions(Set<AttemptQuestion> attemptQuestions) { this.attemptQuestions = attemptQuestions; }

    @Override
    public String toString() {
        return "Attempt{id=" + id + ", user=" + (user == null ? null : user.getUsername()) + ", attemptDate=" + attemptDate + "}";
    }
}
