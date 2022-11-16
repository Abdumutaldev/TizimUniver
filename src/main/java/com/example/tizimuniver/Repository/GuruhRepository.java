package com.example.tizimuniver.Repository;

import com.example.tizimuniver.Entity.Guruh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuruhRepository extends JpaRepository<Guruh,Integer> {
boolean existsByGuruhRaqami(String guruhraqami);
 Optional<Guruh> findById(Integer id);
}
