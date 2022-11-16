package com.example.tizimuniver.Repository;

import com.example.tizimuniver.Entity.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManzilRepository extends JpaRepository<Manzil, Integer> {
    boolean existsByVilAndTumAndKochaAndUy(String vil, String tum, String kocha, String uy);
    Optional<Manzil> findById(Integer id);
}

