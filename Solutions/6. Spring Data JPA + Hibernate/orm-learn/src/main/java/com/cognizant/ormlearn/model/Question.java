package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qs_id")
    private int id;

    @Column(name = "qs_text")
    private String text;

    @OneToMany(mappedBy = "question")
    private Set<Options> options;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Set<Options> getOptions() { return options; }
    public void setOptions(Set<Options> options) { this.options = options; }

    @Override
    public String toString() {
        return "Question{id=" + id + ", text='" + text + "'}";
    }
}
