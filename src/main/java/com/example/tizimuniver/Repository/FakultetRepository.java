package com.example.tizimuniver.Repository;

import com.example.tizimuniver.Entity.Fakultet;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet ,Integer> {
// boolean existsByIdAndNomi(Integer id, String nomi);
  //  Optional<Fakultet> findByNomi(String nomi);
    Optional<Fakultet> findByNomiAndUniversitetId(String nomi, Integer universitet_id);
}
