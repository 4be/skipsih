package com.sekoding.example.demo.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SuccessResponse {

    private int status;
    private String message;
    private Object data;

    public SuccessResponse(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

}
