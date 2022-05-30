package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping("/hcms")
    public String index(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/hcms/dataketerangan")
    public String dataKeterangan(Model model) {
        model.addAttribute("title", "Data Keterangan Sakit");
        return "dataKeterangan/index";
    }

    @GetMapping("/hcms/dataclock")
    public String index2(Model model) {
        model.addAttribute("title", "Data Clock");
        return "dataClock/index";
    }

    @GetMapping("/hcms/stories")
    public String stories(Model model) {
        model.addAttribute("title", "Data Stories");
        return "stories/index";
    }

    @GetMapping("/hcms/create")
    public String createUser(Model model) {
        model.addAttribute("title", "Tambah User");
        return "user/create_user";
    }

    @GetMapping("/hcms/update/{nik}")
    public String updateUser(Model model, @PathVariable String nik) {
        model.addAttribute("title", "Ubah User");
        User user = userRepository.findUserByNik(nik);
        model.addAttribute("user", user);
        return "user/ubah_user";
    }

    @GetMapping("/hcms/datauser")
    public String dataUser(Model model) {
        model.addAttribute("title", "Data User");
        return "user/data_user";
    }


    @GetMapping("/hcms/userabsen")
    public String userAbsen(Model model) {
        model.addAttribute("title", "Data absen");
        return "user/absen_user";
    }

    @GetMapping("/hcms/penilaian")
    public String penilaian(Model model) {
        model.addAttribute("title", "Data penilaian");
        return "user/penilaian";
    }
}
