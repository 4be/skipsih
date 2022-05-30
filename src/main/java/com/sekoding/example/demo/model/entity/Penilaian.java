package com.sekoding.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "tbl_penilaian")
public class Penilaian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Produktifitas

    @Column(length = 300)
    private String Kualitas_Kerja;

    @Column(length = 300)
    private String Kuantitas_Kerja;

    @Column(length = 300)
    private String Profesionalisme;

    @Column(length = 300)
    private String Inisiatif;

    //SIKAP KERJA
    @Column(length = 300)
    private String Integritas;

    @Column(length = 300)
    private String Kerja_Sama;

    @Column(length = 300)
    private String Etika;

    @Column(length = 300)
    private String Disiplin;

    @Column(length = 300)
    private String Kesehatan_Kerja;

    //Manajerial

    @Column(length = 300)
    private String Perencanaan;

    @Column(length = 300)
    private String Inovasi;

    @Column(length = 300)
    private String Pengawasan;

    @Column(length = 300)
    private String Kepemimpinan;

    private LocalDateTime date_inputed;

    @ManyToOne
    private User penilai;

    @ManyToOne
    private User ternilai;





}
