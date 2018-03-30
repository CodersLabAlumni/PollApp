package com.pollapp.validation;

import com.pollapp.entity.Answer;
import com.pollapp.response.AnswerFormValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class AnswerValidation {

    @Autowired
    AnswerFormValidationResponse answerFormValidationResponse;

    public boolean validAnswer(Answer answer, BindingResult bindingResult) {
        answerFormValidationResponse.getErrors().clear();
        answerFormValidationResponse.setStatus(HttpStatus.OK);
        if (bindingResult.hasErrors()) {
            for (FieldError f : bindingResult.getFieldErrors()) {
                answerFormValidationResponse.getErrors().put(f.getField(), f.getDefaultMessage());
            }
            answerFormValidationResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
            return false;
        }
        return true;
    }

    public AnswerFormValidationResponse getAnswerFormValidationResponse() {
        return answerFormValidationResponse;
    }
}
