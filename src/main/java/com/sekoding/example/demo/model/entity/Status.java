package com.sekoding.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private Long userId;

    @Column(length = 20)
    private String nik;

    @Column(length = 20)
    private String nama;

    @Column(length = 20)
    private Boolean userStatus;



    public Status(Long userId, String nik, String nama, Boolean userStatus) {
        this.userId = userId;
        this.nik = nik;
        this.nama = nama;
        this.userStatus = userStatus;
    }
}
