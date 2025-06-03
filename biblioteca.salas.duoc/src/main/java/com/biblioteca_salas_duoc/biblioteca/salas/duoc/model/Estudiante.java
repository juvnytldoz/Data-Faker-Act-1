package com.biblioteca_salas_duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estudiante {
    @Id
    private Integer id;
    private String run;
    private String nombres;
    private String correo;
    private char jornada;
    private int telefono;

    @ManyToOne// Indica que muchos objetos de esta entidad están relacionados con uno solo de otra entidad (relación muchos a uno)
    private Carrera carrera;
}

