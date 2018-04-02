package com.pollapp.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "poll")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank
    @NotEmpty
    private String question;

    @Column(columnDefinition= "timestamp with time zone")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @Column(columnDefinition= "timestamp with time zone")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar closed;

    @JoinColumn(name = "account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserAccount userAccount;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(joinColumns = {@JoinColumn(name = "poll_id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id")})
    private Set<Category> categories;

    public Poll() {
        categories = new HashSet<>();
        created = Calendar.getInstance();
        closed = Calendar.getInstance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getClosed() {
		return closed;
	}

	public void setClosed(Calendar closed) {
		this.closed = closed;
	}

	public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
