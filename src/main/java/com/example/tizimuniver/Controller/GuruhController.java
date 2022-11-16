package com.example.tizimuniver.Controller;

import com.example.tizimuniver.Entity.Fakultet;
import com.example.tizimuniver.Entity.Fanlar;
import com.example.tizimuniver.Entity.Guruh;
import com.example.tizimuniver.Paylout.FakultetDto;
import com.example.tizimuniver.Paylout.GuruhDto;
import com.example.tizimuniver.Repository.FanlarRepository;
import com.example.tizimuniver.Repository.GuruhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guruh")
public class GuruhController {
    @Autowired
    GuruhRepository guruhRepository;

    @Autowired
    FanlarRepository fanlarRepository;

    @PostMapping("/joylash")
    public  String joylash(@RequestBody GuruhDto guruhDto){
        boolean b = guruhRepository.existsByGuruhRaqami(guruhDto.getGuruhRaqami());
        if(b) return "Bunday guruh mavjud";
        Guruh guruh=new Guruh();
        guruh.setNomi(guruhDto.getNomi());
        guruh.setGuruhRaqami(guruhDto.getGuruhRaqami());
        Fakultet fakultet=new Fakultet();
        fakultet.setId(guruhDto.getFakultetId());
        guruh.setFakultet(fakultet);
        guruhRepository.save(guruh);
        List<Fanlar> fanlarList=new ArrayList<>();
        for (Fanlar fanlar : guruhDto.getFanlarList()) {
                Fanlar fanlar1=new Fanlar();
                fanlar1.setNomi(fanlar.getNomi());
                fanlar1.setGuruhList(guruh);
                fanlarList.add(fanlar1);
                fanlarRepository.save(fanlar1);
        }
        guruh.setFanlar(fanlarList);
        guruhRepository.save(guruh);


        return "Guruh malumotlari joylandi";

    }
    @GetMapping("/oqib_olish")
    public List<Guruh> oqish(){
        List<Guruh> guruhList=guruhRepository.findAll();
        return guruhList;
    }

    @GetMapping("/id_oqish/{id}")
    public Optional<Guruh> oqish(@PathVariable Integer id){
        Optional<Guruh> guruhOptional=guruhRepository.findById(id);
        if (!guruhOptional.isPresent()) return null;
        return guruhOptional;
    }
    @PutMapping("/tahrirlash/{id}")
    public String tahrirlash(@PathVariable Integer id, @RequestBody GuruhDto guruhDto){
        Optional<Guruh> guruhOptional=guruhRepository.findById(id);
        if (!guruhOptional.isPresent()) return "Bunday id li  malumotlar topilmadi";
        Guruh guruh=guruhOptional.get();
        guruh.setGuruhRaqami(guruhDto.getGuruhRaqami());
        guruh.setNomi(guruhDto.getNomi());
        Fakultet fakultet=guruhOptional.get().getFakultet();
        fakultet.setId(guruhDto.getFakultetId());
        guruh.setFakultet(fakultet);
        guruhRepository.save(guruh);
        return "Malumotlar tahrirlandi";

    }
    @DeleteMapping("/delete/{id}")
    public String ochirish(@PathVariable Integer id){
        Optional<Guruh> byId = guruhRepository.findById(id);
        if (!byId.isPresent())return "Bunday idli guruh topilmadi";
        guruhRepository.deleteById(id);
        return id+" idli guruh malumotlari o`chirildi";
    }
}
