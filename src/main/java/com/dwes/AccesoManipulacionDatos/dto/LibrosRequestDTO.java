package com.dwes.AccesoManipulacionDatos.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LibrosRequestDTO {
    private List<LibroRequestDTO> libros;
    private Integer cantidadTotal;
}
