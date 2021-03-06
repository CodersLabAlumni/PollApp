package com.pollapp.repository;

import com.pollapp.entity.Comment;
import com.pollapp.entity.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPollId(long id);

    Page<Comment> findByPollId(long id, Pageable pageable);

    long countByPoll(Poll poll);

    long countByPollId(long id);
}
