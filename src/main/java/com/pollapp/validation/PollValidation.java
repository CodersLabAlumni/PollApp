package com.pollapp.validation;

import com.pollapp.entity.Poll;
import com.pollapp.response.PollFormValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class PollValidation {

    @Autowired
    PollFormValidationResponse pollFormValidationResponse;

    public boolean validPoll(Poll poll, BindingResult bindingResult) {
        pollFormValidationResponse.getErrors().clear();
        pollFormValidationResponse.setStatus(HttpStatus.OK);
        if (bindingResult.hasErrors()) {
            for (FieldError f : bindingResult.getFieldErrors()) {
                pollFormValidationResponse.getErrors().put(f.getField(), f.getDefaultMessage());
            }
            pollFormValidationResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
            return false;
        }
        return true;
    }

    public PollFormValidationResponse getPollFormValidationResponse() {
        return pollFormValidationResponse;
    }
}
