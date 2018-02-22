package com.pollapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private Poll poll;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "answers", cascade = CascadeType.MERGE)
    private Set<UserData> data;

    public Answer() {
        data = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Set<UserData> getData() {
        return data;
    }

    public void setData(Set<UserData> data) {
        this.data = data;
    }
}
