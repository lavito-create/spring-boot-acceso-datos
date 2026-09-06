package com.dwes.AccesoManipulacionDatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpleadoRequestDTO {
    @NotBlank(message = "Este campo debe ser obligatorio")
    private String nombre;
    private String apellido;

    @Email
    private String correo;
}
