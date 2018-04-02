package com.pollapp.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "gamescore")
public class GameScore {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String name;
	
	private long score;

	@Column(columnDefinition= "timestamp with time zone")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;

	public GameScore() {
		super();
		this.created = Calendar.getInstance();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}
	
}
