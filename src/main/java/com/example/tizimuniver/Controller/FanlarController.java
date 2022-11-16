package com.example.tizimuniver.Controller;

import com.example.tizimuniver.Entity.Fanlar;
import com.example.tizimuniver.Entity.Guruh;
import com.example.tizimuniver.Paylout.FanlarDto;
import com.example.tizimuniver.Repository.FanlarRepository;
import com.example.tizimuniver.Repository.GuruhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ganlar")
public class FanlarController {
    @Autowired
    FanlarRepository fanlarRepository;
    @Autowired
    GuruhRepository guruhRepository;
    @PostMapping("/joylash")
    public String joylash(@RequestBody FanlarDto fanlarDto){
        boolean b = fanlarRepository.existsByNomi(fanlarDto.getNomi());
        if (b) return "Bunday fan mavjud";
        Fanlar fanlar=new Fanlar();
        fanlar.setNomi(fanlarDto.getNomi());
        fanlarRepository.save(fanlar);
        return "Fan joylandi";

    }
    @GetMapping("/oqib_olish")
    public List<Fanlar> oqish(){
        List<Fanlar> fanlarList=fanlarRepository.findAll();
        return fanlarList;
    }
    @GetMapping("/oqish/{id}")
    public Optional<Fanlar> oqish(@PathVariable Integer id){
        Optional<Fanlar> optionalFanlar=fanlarRepository.findById(id);
        if (!optionalFanlar.isPresent()) return null;
        return optionalFanlar;
    }
    @DeleteMapping("/ochirish/{id}")
    public String ochirish(@PathVariable Integer id){
        Optional<Fanlar> byId = fanlarRepository.findById(id);
        if (!byId.isPresent()) return "Bunday fan mavjud emas";
        fanlarRepository.deleteById(id);
        return  id+" idli kitob ochirildi";

    }
    @PutMapping("/tahrirlash/{id}")
    public String tahrirlash(@PathVariable Integer id,@RequestBody FanlarDto fanlarDto){
        Optional<Fanlar> byId = fanlarRepository.findById(id);
        if (!byId.isPresent()) return "Bunday id da malumot mavjud emas";
        Fanlar fanlar = byId.get();
        fanlar.setNomi(fanlarDto.getNomi());
        fanlarRepository.save(fanlar);
        return id+" idli fan malumotlari tahrirlandi";

    }
}
