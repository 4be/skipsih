package com.sekoding.example.demo.model.response.payload;

import com.sekoding.example.demo.model.entity.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RoleResponse {

    private Integer id;
    private ERole rolename;

}
