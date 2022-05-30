package com.sekoding.example.demo.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FailedResponse {

    private int status;
    private String message;

    public FailedResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

}
