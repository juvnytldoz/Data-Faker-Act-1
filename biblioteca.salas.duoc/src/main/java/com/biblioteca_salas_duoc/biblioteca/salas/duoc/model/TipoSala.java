package com.biblioteca_salas_duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TipoSala {
    @Id
    private Integer idTipo;
    private String nombre;
}
