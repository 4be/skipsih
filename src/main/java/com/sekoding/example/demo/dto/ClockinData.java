package com.sekoding.example.demo.dto;


import com.sekoding.example.demo.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ClockinData {
    private String times;
    private User user_id ;
}
