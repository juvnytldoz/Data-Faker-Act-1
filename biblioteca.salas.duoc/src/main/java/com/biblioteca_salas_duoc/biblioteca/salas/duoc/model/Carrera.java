package com.biblioteca_salas_duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nombre;
}