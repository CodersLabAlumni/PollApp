package com.pollapp.repository;

import com.pollapp.entity.Poll;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

	List<Poll> findAllByClosedAfter(Calendar date);
	List<Poll> findAllByClosedBeforeOrClosedIsNull(Calendar date);
	List<Poll> findAllByCategoriesId(int categoryId);
}
