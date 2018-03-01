package com.pollapp.repository;

import com.pollapp.entity.Poll;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

	List<Poll> findAllByCategoriesId(int categoryId);

	Page<Poll> findAllByCategoriesId(int categoryId, Pageable pageable);

	List<Poll> findByIdNotIn(List<Long> pollIdList);

    List<Poll> findByIdNotInAndCategoriesId(List<Long> pollIdList, int categoryId);
}
