package com.example.tizimuniver.Controller;

import com.example.tizimuniver.Entity.Manzil;
import com.example.tizimuniver.Entity.Universitet;
import com.example.tizimuniver.Paylout.UniversitetDto;
import com.example.tizimuniver.Repository.ManzilRepository;
import com.example.tizimuniver.Repository.UniversitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/universitet")
public class UniversitetController {
    @Autowired
    UniversitetRepository universitetRepository;

    @Autowired
    ManzilRepository manzilRepository;

    @PostMapping("/joylash")
    public  String joylash(@RequestBody UniversitetDto universitetDto){
        Universitet universitet=new Universitet();
        Optional<Universitet> byNomiAndManzil_uy = universitetRepository.findByNomi(universitetDto.getNomi());
        if(byNomiAndManzil_uy.isPresent()) return "Bunday universitet mavjud";
        boolean optionalManzil=manzilRepository.existsByVilAndTumAndKochaAndUy(universitetDto.getVil(),universitetDto.getTum(),universitetDto.getKocha(),universitetDto.getUy());
        if (optionalManzil) return "Bunday manzil mavjud";
        Manzil manzil=new Manzil();
        manzil.setVil(universitetDto.getVil());
        manzil.setTum(universitetDto.getTum());
        manzil.setKocha(universitetDto.getKocha());
        manzil.setUy(universitetDto.getUy());
        Manzil save = manzilRepository.save(manzil);
        universitet.setNomi(universitetDto.getNomi());
        universitet.setManzil(save);
        universitetRepository.save(universitet);
        return "Universitet malumotlari qoshildi";

    }

    @PutMapping("/tahrirlash/{id}")
    public String tahrirlash(@PathVariable Integer id,@RequestBody UniversitetDto universitetDto){
        Optional<Universitet> universitetOptional=universitetRepository.findById(id);
        if (!universitetOptional.isPresent()) return "Bunday id li malumotlar topilmadi";
        Optional<Universitet> byNomiAndManzil_uy = universitetRepository.findByNomi(universitetDto.getNomi());
        if (byNomiAndManzil_uy.isPresent()) return "Bunday universitet mavjud";
        Universitet universitet = universitetOptional.get();
        universitet.setNomi(universitetDto.getNomi());
        Manzil manzil= universitetOptional.get().getManzil();
        manzil.setVil(universitetDto.getVil());
        manzil.setTum(universitetDto.getTum());
        manzil.setKocha(universitetDto.getKocha());
        manzil.setUy(universitetDto.getUy());
        universitet.setManzil(manzil);
        universitetRepository.save(universitet);
        return "Malumotlar tahrirlandi";
    }
    @GetMapping("/oqib_olish")
    public List<Universitet> oqibolish(){
        List<Universitet> universitetList=universitetRepository.findAll();
        return universitetList;
    }
    @GetMapping("/id_oqish/{id}")
    public Optional<Universitet> oqish(@PathVariable Integer id){
        Optional<Universitet> universitetOptional=universitetRepository.findById(id);
        if (!universitetOptional.isPresent()) return null;
        return universitetOptional;
    }
    @DeleteMapping("/ochirish/{id}")
    public String ochirish(@PathVariable Integer id ){
        Optional<Universitet> byId = universitetRepository.findById(id);
        if (!byId.isPresent()) return "Bunday id li universitet avvaldan yoq";
        Universitet universitet = byId.get();
        universitetRepository.delete(universitet);
        Manzil manzil = byId.get().getManzil();
        manzilRepository.delete(manzil);
        return "Malumotlar ochirildi";
    }


}
