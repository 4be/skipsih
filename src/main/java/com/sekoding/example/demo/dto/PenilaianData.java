package com.sekoding.example.demo.dto;

import com.sekoding.example.demo.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class PenilaianData {

    @Autowired

    private String Kualitas_Kerja;


    private String Kuantitas_Kerja;


    private String Profesionalisme;


    private String Inisiatif;

    //SIKAP KERJA

    private String Integritas;


    private String Kerja_Sama;


    private String Etika;


    private String Disiplin;


    private String Kesehatan_Kerja;

    //Manajerial


    private String Perencanaan;

    private String Inovasi;

    private String Pengawasan;

    private String Kepemimpinan;

    private LocalDateTime date_inputed;

    private User nik_penilai;

    private User nik_ternilai;

}
