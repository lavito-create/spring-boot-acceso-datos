package com.dwes.AccesoManipulacionDatos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequestDTO {
    private String nombre;
    private String correo;
}
