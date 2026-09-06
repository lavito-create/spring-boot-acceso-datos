package com.dwes.AccesoManipulacionDatos.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductoRequestDTO {
    private String nombre;
    private Double precio;
    private Integer cantidad_disponible;
}
