package com.pollapp.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private Calendar created;

    @JoinColumn(name = "account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Poll poll;

    public Comment() {
        created = Calendar.getInstance();
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

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
