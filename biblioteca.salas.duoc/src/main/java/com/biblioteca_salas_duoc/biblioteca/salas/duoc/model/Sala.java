package com.biblioteca_salas_duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sala {
    @Id
    private Integer codigo;
    private String nombre;
    private Integer capacidad;
    private Integer idInstituo;

    @ManyToOne // Indica que muchos objetos de esta entidad están relacionados con uno solo de otra entidad (relación muchos a uno)
    private TipoSala tipoSala;
}
