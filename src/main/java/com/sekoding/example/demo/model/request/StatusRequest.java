package com.sekoding.example.demo.model.request;

import lombok.Data;

@Data
public class StatusRequest {

    private Long user_id;
    private String nik;
    private String nama;
    private Boolean status;

}
