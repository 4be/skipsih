package com.sekoding.example.demo.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "tbl_clock")
public @Data
class Clock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime times;

    private Boolean working = false;

    @ManyToOne
    private User user_id;

}


