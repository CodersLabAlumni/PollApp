package com.pollapp.repository;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    long countByAnswersPoll(Poll poll);

    long countByAnswersPollId(long id);

    long countByAnswersId(long id);

    long countByAnswers(Answer answer);

}
