package com.dwes.AccesoManipulacionDatos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VueloTotalRequestDTO {
    private Long id;
    private Integer capacidad;
    private Double precio;
    private Double total;
}
