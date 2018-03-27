package com.pollapp.validation;

import com.pollapp.entity.Comment;
import com.pollapp.response.CommentValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class CommentValidation {

    @Autowired
    CommentValidationResponse commentValidationResponse;

    public boolean validComment(Comment comment, BindingResult bindingResult) {
        commentValidationResponse.getErrors().clear();
        commentValidationResponse.setStatus(HttpStatus.OK);
        if (bindingResult.hasErrors()) {
            for (FieldError f : bindingResult.getFieldErrors()) {
                commentValidationResponse.getErrors().put(f.getField(), f.getDefaultMessage());
            }
            commentValidationResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
            return false;
        }
        return true;
    }

    public CommentValidationResponse getCommentValidationResponse() {
        return commentValidationResponse;
    }
}
