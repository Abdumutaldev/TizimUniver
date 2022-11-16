package com.example.tizimuniver.Repository;

import com.example.tizimuniver.Entity.Talaba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalabaRepository extends JpaRepository<Talaba ,Integer> {
boolean existsByTelNomer(String telNomer);
}
