package com.pollapp.controller;

import com.pollapp.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @GetMapping("")
    public List<Comment> getComments() {
        // TODO
        return null;
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable long commentId) {
        // TODO
        return null;
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable long commentId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{commentId}")
    public Comment deleteComment(@PathVariable long commentId) {
        // TODO
        return null;
    }
}
