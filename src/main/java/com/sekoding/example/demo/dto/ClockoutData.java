package com.sekoding.example.demo.dto;


import com.sekoding.example.demo.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ClockoutData {
    private String times;
    private String location_clock;
    private String coordinate;
    private Long level_kesehatan_fisik_id;
    private Long level_kesehatan_mental_Id;
    private MultipartFile picture;
    private User user_id ;
}
