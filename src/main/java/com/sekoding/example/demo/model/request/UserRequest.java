package com.sekoding.example.demo.model.request;

import lombok.Data;

@Data
public class UserRequest {

    private String nama;
    private String nik;
    private String alamat;
    private String tanggal_lahir;
    private String email;
    private String password;
    private String divisi;
    private String nik_manager;
    private String role;
    private Long salary;

}
