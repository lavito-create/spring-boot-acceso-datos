package com.dwes.AccesoManipulacionDatos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "El origen debe ser obligatorio")
    private String origen;

    @NotBlank(message = "El destino debe ser obligatorio")
    private String destino;

    @NotNull(message = "La fecha-hora de salida debe ser obligatoria")
    private LocalDateTime fechaSalida;

    @NotNull(message = "La fecha-hora de llegada debe ser obligatoria")
    private LocalDateTime fechaLlegada;

    @Positive(message = "La capacidad debe ser positiva")
    private Integer capacidadAsientos;

    @Positive(message = "El precio debe ser positiva")
    private Double precio;
}
