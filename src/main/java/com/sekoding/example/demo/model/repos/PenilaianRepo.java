package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Penilaian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PenilaianRepo extends CrudRepository<Penilaian,Long> {
    @Query(value = "select * from tbl_stories ORDER BY ID DESC", nativeQuery = true)
    public List<Penilaian> findAllDesc();

}
