package com.dwes.AccesoManipulacionDatos.model.compositePK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoSeccionId implements Serializable {
    private Long id_empleado;
    private Long id_seccion;
}
