package com.example.tizimuniver.Repository;

import com.example.tizimuniver.Entity.Universitet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversitetRepository extends JpaRepository<Universitet,Integer> {
 boolean existsByNomi(String nomi);
 Optional<Universitet> findByNomi(String nomi);

}
