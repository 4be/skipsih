package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.entity.Penilaian;
import com.sekoding.example.demo.model.repos.PenilaianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenilaianServices {

    @Autowired
    private PenilaianRepo penilaianRepo;

    public Penilaian create(Penilaian penilaian){
        return penilaianRepo.save(penilaian);
    }

    public Iterable<Penilaian> findAll(){
        return penilaianRepo.findAll();
    }

    public List<Penilaian> findAllDesc(){
        return penilaianRepo.findAllDesc();
    }

    public void remmoveOne(Long id){
        penilaianRepo.deleteById(id);
    }

    public Penilaian findById(Long id){
        return penilaianRepo.findById(id).get();
    }



}
