package com.dwes.AccesoManipulacionDatos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoEmpleadoRequestDTO {
    private Double salarioMedio;
    private Double salarioMaximo;
    private Double salarioMinimo;
}
