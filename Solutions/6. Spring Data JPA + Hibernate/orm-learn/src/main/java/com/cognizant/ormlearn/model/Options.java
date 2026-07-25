package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "options")
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "op_qs_id")
    private Question question;

    @Column(name = "op_text")
    private String text;

    // Points awarded when this option is selected: 0 for a wrong option, >0 for a correct/partial-credit option
    @Column(name = "op_score")
    private BigDecimal score;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public BigDecimal getScore() { return score; }
    public void setScore(BigDecimal score) { this.score = score; }

    @Override
    public String toString() {
        return "Options{id=" + id + ", text='" + text + "', score=" + score + "}";
    }
}
