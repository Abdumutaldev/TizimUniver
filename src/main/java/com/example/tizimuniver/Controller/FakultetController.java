package com.example.tizimuniver.Controller;

import com.example.tizimuniver.Entity.Fakultet;
import com.example.tizimuniver.Entity.Universitet;
import com.example.tizimuniver.Paylout.FakultetDto;
import com.example.tizimuniver.Repository.FakultetRepository;
import com.example.tizimuniver.Repository.UniversitetRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fakultet")
public class FakultetController {
    @Autowired
    FakultetRepository fakultetRepository;

    @Autowired
    UniversitetRepository universitetRepository;

    @PostMapping("/joylash")
    public String joylash(@RequestBody FakultetDto fakultetDto){
        Optional<Fakultet> byNomiAndUniversitetId = fakultetRepository.findByNomiAndUniversitetId(fakultetDto.getNomi(), fakultetDto.getUniversitetId());
        if (byNomiAndUniversitetId.isPresent()) return "Bunday fakultet mavjud";
        Fakultet fakultet=new Fakultet();
        Universitet universitet=new Universitet();
        universitet.setId(fakultetDto.getUniversitetId());
        fakultet.setNomi(fakultetDto.getNomi());
        fakultet.setUniversitet(universitet);
        fakultetRepository.save(fakultet);
        return "Fakultet malumotlari qoshildi";

    }
    @PutMapping("/tahrirlash/{id}")
    public  String tahrirlash(@PathVariable Integer id,@RequestBody FakultetDto fakultetDto){

            Optional<Fakultet> optionalFakultet=fakultetRepository.findById(id);
            if (!optionalFakultet.isPresent()) return "Bunday id li malumotlar topilmadi";
            Fakultet fakultet = optionalFakultet.get();
        Optional<Fakultet> byNomi = fakultetRepository.findByNomiAndUniversitetId(fakultetDto.getNomi(),fakultetDto.getUniversitetId());
        if (byNomi.isPresent()) return "Bunday fakultet mavjud";
        fakultet.setNomi(fakultetDto.getNomi());
            fakultetRepository.save(fakultet);
            return "Malumotlar tahrirlandi";


    }
    @GetMapping("/oqibolish")
    public List<Fakultet> oqibolish(){
        List<Fakultet> fakultetList=fakultetRepository.findAll();
        return fakultetList;
    }
    @GetMapping("/oqib_olish/{id}")
    public Optional<Fakultet> oqish(@PathVariable Integer id){
        Optional<Fakultet> optionalFakultet=fakultetRepository.findById(id);
        if (!optionalFakultet.isPresent()) return null;
        return optionalFakultet;

    }
    @DeleteMapping("/ochirish/{id}")
    public String ochirish(@PathVariable Integer id){
        Optional<Fakultet> byId = fakultetRepository.findById(id);
        if (!byId.isPresent()) return "Bunday isli fakultet yoq";
        fakultetRepository.deleteById(id);
//        Fakultet fakultet = byId.get();
//        fakultetRepository.delete(fakultet);
        return id+" idli fakultet malumotlari ochirildi";
    }
}

