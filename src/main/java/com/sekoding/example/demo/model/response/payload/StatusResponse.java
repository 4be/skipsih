package com.sekoding.example.demo.model.response.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatusResponse {

    private Long user_id;
    private String nik;
    private String nama;
    private Boolean status;

}
