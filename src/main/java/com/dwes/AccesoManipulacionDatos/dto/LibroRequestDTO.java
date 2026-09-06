package com.dwes.AccesoManipulacionDatos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibroRequestDTO {
    private String titulo;
    private String autor;
}
