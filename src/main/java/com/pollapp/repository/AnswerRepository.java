package com.pollapp.repository;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByPollId(long id);

    List<Answer> findByPoll(Poll poll);
}
