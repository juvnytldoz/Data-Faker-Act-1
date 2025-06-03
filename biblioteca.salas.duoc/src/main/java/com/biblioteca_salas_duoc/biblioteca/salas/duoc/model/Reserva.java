package com.biblioteca_salas_duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Reserva {
    @Id
    private Integer id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Sala sala;

    @Temporal(TemporalType.TIMESTAMP)  // Indica que este atributo Date se mapeará como un valor con fecha y hora (timestamp) en la base de datos

    private Date fechaSolicitada;      // Campo que almacena una fecha completa con hora (por ejemplo: 2025-06-01 14:30:00)
    private Date horaSolicitada;
    private Date horaCierre;
    private int estado;
}
