package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.dto.ClockinData;
import com.sekoding.example.demo.dto.ClockoutData;
import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.model.entity.Clock;
import com.sekoding.example.demo.services.ClockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("api/")
public class ClockController {

    @Autowired
    private ClockService clockService;

    @Autowired
    private ModelMapper modelMapper;

//        private static String UPLOADED_PATH = "/Users/HP/Desktop/springHCM/src/main/resources/static/images/";
    private static String UPLOADED_PATH = "/home/adiabdurrakh/opt/sinarmas/demo/public/img/";

    @PostMapping("/clockin")
    public ResponseEntity<ResponseData<Clock>> clockin(@ModelAttribute ClockinData clockinData, Errors errors) {

        ResponseData<Clock> responseData = new ResponseData<>();
        Clock clockinr = new Clock();
        Date date = new Date();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        clockinr.setTimes(java.time.LocalDateTime.now());
        clockinr.setWorking(true);
        clockinr.setUser_id(clockinData.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(clockService.create(clockinr, Boolean.TRUE));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }


    @PostMapping("/clockout")
    public ResponseEntity<ResponseData<Clock>> clockout(@Valid @RequestParam("picture") MultipartFile picture, @ModelAttribute ClockoutData clockoutData, Errors errors) {

        ResponseData<Clock> responseData = new ResponseData<>();
        Clock clockinr = new Clock();
        Date date = new Date();

        try {
            byte[] bytes = picture.getBytes();
            Path path = Paths.get((UPLOADED_PATH) + date.getTime() + picture.getOriginalFilename());
            Files.write(path, bytes);
            String urlImage = "35.209.242.226/img/" + date.getTime() + picture.getOriginalFilename();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        clockinr.setTimes(java.time.LocalDateTime.now());
        clockinr.setWorking(false);
        clockinr.setUser_id(clockoutData.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(clockService.create(clockinr, Boolean.FALSE));
        return ResponseEntity.ok(responseData);
    }


    public Iterable<Clock> findAll() {
        return clockService.findAll();
    }


    @GetMapping("/time")
    public String getWaktu() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
        Date date = new Date();
        String test = date.toString();
        return test;
    }

    @GetMapping("/clockin/{id}")
    public Clock findOne(@PathVariable("id") Long id) {
        return clockService.findByid(id);
    }

    @PutMapping("/clockin")
    public Clock update(@RequestBody Clock clockin) {
        return clockService.create(clockin, Boolean.TRUE);
    }

    @DeleteMapping("/clockin/delete/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        clockService.removeOne(id);
    }

    @GetMapping("/clock/desc")
    public List<Clock> getClockDesc() {
        return clockService.findAllDesc();
    }

    @GetMapping("/clock/last/{id}")
    public List<Clock> getLastClock(@PathVariable("id") Long id) {
        return clockService.findByIdDesc(id);
    }

    @GetMapping("/clock/history/{id}")
    public List<Clock> getHistoryClock(@PathVariable("id") Long id) {
        return clockService.findByIdHistory(id);
    }

    @GetMapping("/clock/team/{team}")
    public List<Clock> getClockTeam(@PathVariable("team") String team) {
        return clockService.findByTeam(team);
    }

    @GetMapping("/clock/totalclock/hour/{jam}")
    public List<String> getTotalClockbyMonth(@PathVariable("jam") Long jam){
        return clockService.getClockMonth(jam);
    }

    @GetMapping("/clock/totalclockyesterday/hour/{jam}")
    public List<String> getTotalClockLastMonth(@PathVariable("jam") Long jam){
        return clockService.getClockMonthMin(jam);
    }

    @GetMapping("/clock/total/month/{month}")
    public List<String> getTotalPerMonth(@PathVariable("month") Long month){
        return clockService.getClockPerMonth(month);
    }



}


