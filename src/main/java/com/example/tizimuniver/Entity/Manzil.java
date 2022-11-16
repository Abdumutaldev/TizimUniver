package com.example.tizimuniver.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Manzil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String vil;
    @Column(nullable = false)
    private String tum;
    @Column(nullable = false)
    private String kocha;
    @Column(nullable = false)
    private String uy;

}
