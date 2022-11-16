package com.example.tizimuniver.Paylout;

import com.example.tizimuniver.Entity.Fanlar;
import lombok.Data;

import java.util.List;

@Data
public class GuruhDto {
    private  String nomi;
    private String guruhRaqami;
    private Integer fakultetId;
    private List<Fanlar> fanlarList;
}
