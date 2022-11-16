package com.example.tizimuniver.Repository;

import com.example.tizimuniver.Entity.Fanlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanlarRepository extends JpaRepository<Fanlar ,Integer> {
    boolean existsByNomi(String nomi);

}
