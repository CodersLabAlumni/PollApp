package com.pollapp.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollapp.entity.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

	List<Poll> findAllByClosedAfter(Calendar date);
	Page<Poll> findAllByClosedBeforeOrClosedIsNull(Calendar date, Pageable pageable);
	List<Poll> findAllByCategoriesId(int categoryId);
	Page<Poll> findAllByCategoriesId(int categoryId, Pageable pageable);
	List<Poll> findAllByCategoriesIdAndClosedAfter(int categoryId, Calendar instance);
	List<Poll> findAllByClosedBeforeOrClosedIsNullAndCategoriesId(Calendar instance, int categoryId);
	List<Poll> findByIdNotIn(List<Long> pollIdList);
  List<Poll> findByIdNotInAndCategoriesId(List<Long> pollIdList, int categoryId);
}
