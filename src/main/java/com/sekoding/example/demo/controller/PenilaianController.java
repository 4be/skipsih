package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.dto.PenilaianData;
import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.model.entity.ERole;
import com.sekoding.example.demo.model.entity.Penilaian;
import com.sekoding.example.demo.model.entity.Role;
import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.repository.RoleRepository;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.services.PenilaianServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/penilaian/")
public class PenilaianController {
    @Autowired
    private PenilaianServices penilaianServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/add")
    public ResponseEntity<ResponseData<Penilaian>> add(@Valid @ModelAttribute PenilaianData penilaianData, Errors errors) {

        ResponseData<Penilaian> responseData = new ResponseData<>();
        Penilaian penilaian = new Penilaian();

        User user = userRepository.findUserByNik(String.valueOf(penilaianData.getNik_penilai()));

        Set<Role> roles = getRole(String.valueOf(user.getRoles()));

        penilaian.setKualitas_Kerja(penilaianData.getKualitas_Kerja());
        penilaian.setKuantitas_Kerja(penilaianData.getKuantitas_Kerja());
        penilaian.setProfesionalisme(penilaianData.getProfesionalisme());
        penilaian.setInisiatif(penilaianData.getInisiatif());

        penilaian.setIntegritas(penilaianData.getIntegritas());
        penilaian.setKerja_Sama(penilaianData.getKerja_Sama());
        penilaian.setEtika(penilaianData.getEtika());
        penilaian.setDisiplin(penilaianData.getDisiplin());
        penilaian.setKesehatan_Kerja(penilaianData.getKesehatan_Kerja());

        penilaian.setPerencanaan(penilaianData.getPerencanaan());
        penilaian.setInovasi(penilaianData.getInovasi());
        penilaian.setPengawasan(penilaianData.getPengawasan());
        penilaian.setKepemimpinan(penilaianData.getKepemimpinan());

        penilaian.setDate_inputed(java.time.LocalDateTime.now());

        penilaian.setPenilai(penilaianData.getNik_penilai());
        penilaian.setTernilai(penilaianData.getNik_ternilai());


        if(user.getRoles().equals("GM")){

            penilaian.setKualitas_Kerja(penilaianData.getKualitas_Kerja());
            penilaian.setKuantitas_Kerja(penilaianData.getKuantitas_Kerja());
            penilaian.setProfesionalisme(penilaianData.getProfesionalisme());
            penilaian.setInisiatif(penilaianData.getInisiatif());

            penilaian.setIntegritas(penilaianData.getIntegritas());
            penilaian.setKerja_Sama(penilaianData.getKerja_Sama());
            penilaian.setEtika(penilaianData.getEtika());
            penilaian.setDisiplin(penilaianData.getDisiplin());
            penilaian.setKesehatan_Kerja(penilaianData.getKesehatan_Kerja());

            penilaian.setPerencanaan(penilaianData.getPerencanaan());
            penilaian.setInovasi(penilaianData.getInovasi());
            penilaian.setPengawasan(penilaianData.getPengawasan());
            penilaian.setKepemimpinan(penilaianData.getKepemimpinan());

            penilaian.setDate_inputed(java.time.LocalDateTime.now());

            penilaian.setPenilai(penilaianData.getNik_penilai());


        }

        if(user.getRoles().equals("MANAGERPERSONALIA")){
            penilaian.setIntegritas(penilaianData.getIntegritas());
            penilaian.setKerja_Sama(penilaianData.getKerja_Sama());
            penilaian.setEtika(penilaianData.getEtika());
            penilaian.setDisiplin(penilaianData.getDisiplin());
            penilaian.setKesehatan_Kerja(penilaianData.getKesehatan_Kerja());


            penilaian.setDate_inputed(java.time.LocalDateTime.now());

            penilaian.setPenilai(penilaianData.getNik_penilai());

        }

        if(user.getRoles().equals("STAFF")){
            penilaian.setPerencanaan(penilaianData.getPerencanaan());
            penilaian.setInovasi(penilaianData.getInovasi());
            penilaian.setPengawasan(penilaianData.getPengawasan());
            penilaian.setKepemimpinan(penilaianData.getKepemimpinan());

            penilaian.setDate_inputed(java.time.LocalDateTime.now());

            penilaian.setPenilai(penilaianData.getNik_penilai());
            penilaian.setTernilai(penilaianData.getNik_ternilai());

        }
        responseData.setStatus(true);
        responseData.setPayload(penilaianServices.create(penilaian));
        return ResponseEntity.ok((responseData));



    }


    public Set<Role> getRole(String role) {
        Set<Role> roles = new HashSet<>();
        switch (role) {
            case "GM":
                Role adminRole = roleRepository.findByRolename(ERole.GM);
                roles.add(adminRole);
                break;
            case "MANAGERPERSONALIA":
                Role modRole = roleRepository.findByRolename(ERole.MANAGERPERSONALIA);
                roles.add(modRole);
                break;
            default:
                Role defRole = roleRepository.findByRolename(ERole.STAFF);
                roles.add(defRole);
        }
        return roles;
    }




}
