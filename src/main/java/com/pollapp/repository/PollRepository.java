package com.pollapp.repository;

import com.pollapp.entity.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    Page<Poll> findAllByClosedBeforeOrClosedIsNull(Calendar date, Pageable pageable);

    Page<Poll> findAllByCategoriesId(int categoryId, Pageable pageable);

    Page<Poll> findAllByCategoriesIdAndClosedAfter(int categoryId, Calendar instance, Pageable pageable);

    Page<Poll> findAllByCategoriesIdAndClosedAfterAndIdNotIn(int categoryId, Calendar instance, List<Long> pollIdList, Pageable pageable);

    Page<Poll> findAllByClosedAfter(Calendar instance, Pageable pageable);

    Page<Poll> findAllByClosedAfterAndIdNotIn(Calendar instance, List<Long> pollIdList, Pageable pageable);

    Page<Poll> findAllByClosedBeforeOrClosedIsNullAndCategoriesId(Calendar instance, int categoryId, Pageable pageable);
}
