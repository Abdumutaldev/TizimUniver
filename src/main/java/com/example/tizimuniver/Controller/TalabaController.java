package com.example.tizimuniver.Controller;

import com.example.tizimuniver.Entity.Guruh;
import com.example.tizimuniver.Entity.Manzil;
import com.example.tizimuniver.Entity.Talaba;
import com.example.tizimuniver.Paylout.TalabaDto;
import com.example.tizimuniver.Repository.GuruhRepository;
import com.example.tizimuniver.Repository.ManzilRepository;
import com.example.tizimuniver.Repository.TalabaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/talaba")
public class TalabaController {
    @Autowired
    TalabaRepository talabaRepository;

    @Autowired
    ManzilRepository manzilRepository;

    @Autowired
    GuruhRepository guruhRepository;
    @PostMapping("/joylash")
    public String joylash(@RequestBody TalabaDto talabaDto){
        boolean b = talabaRepository.existsByTelNomer(talabaDto.getTelNomer());
        if (b) return "bunday talaba mavjud";
        Talaba talaba=new Talaba();
    talaba.setIsm(talabaDto.getIsm());
    talaba.setFamilya(talabaDto.getFamilya());
    talaba.setTelNomer(talabaDto.getTelNomer());
    Manzil manzil=new Manzil();
    manzil.setVil(talabaDto.getVil());
    manzil.setTum(talabaDto.getTum());
    manzil.setKocha(talabaDto.getKocha());
    manzil.setUy(talabaDto.getUy());
    Manzil save = manzilRepository.save(manzil);
    talaba.setManzil(save);
    Guruh guruh=new Guruh();
    guruh.setId(talabaDto.getGuruhId());
    talaba.setGuruh(guruh);
    talabaRepository.save(talaba);
    return "Joylandi";
    }


    @GetMapping("/oqib_olish")
    public List<Talaba> talabaList(){
        List<Talaba> talabaList=talabaRepository.findAll();
        return talabaList;
    }
    @GetMapping("oqib_olish_id/{id}")
    public Optional<Talaba> oqishId(@PathVariable Integer id){
        Optional<Talaba> talabaOptional=talabaRepository.findById(id);
        if (!talabaOptional.isPresent()) return null;
        return talabaOptional;
    }
    @PutMapping("/tahrirlash/{id}")
    public String tahrirlash(@PathVariable Integer id,@RequestBody TalabaDto talabaDto){
        Optional<Talaba> optionalTalaba=talabaRepository.findById(id);
        if (!optionalTalaba.isPresent()) return "Bunday id li talaba topilmadi";
        Talaba talaba = optionalTalaba.get();
        talaba.setIsm(talabaDto.getIsm());
        talaba.setFamilya(talabaDto.getFamilya());
        talaba.setTelNomer(talabaDto.getTelNomer());
        Manzil manzil=optionalTalaba.get().getManzil();
        manzil.setVil(talabaDto.getVil());
        manzil.setTum(talabaDto.getTum());
        manzil.setKocha(talabaDto.getKocha());
        manzil.setUy(talabaDto.getUy());
        talaba.setManzil(manzil);
        talabaRepository.save(talaba);
        return "Malumotlar tahrirlandi";

    }
    @DeleteMapping("/ochirish/{id}")
    public  String ochirish(@PathVariable Integer id){
        Optional<Talaba> byId = talabaRepository.findById(id);
        if (!byId.isPresent()) return "Bunday id li talaba topilmadi";
        talabaRepository.deleteById(id);
        return id+ " id li talaba malumotlari o`chirildi";

    }



}
