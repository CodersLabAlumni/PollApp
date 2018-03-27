package com.pollapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommentValidationResponse {

    Map<String, String> errors;

    HttpStatus status;

    String successMsg;

    public CommentValidationResponse() {
        errors = new HashMap<>();
        successMsg = "Comment has been successfully added";
        status = HttpStatus.OK;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
