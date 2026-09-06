package com.dwes.AccesoManipulacionDatos.model;

import com.dwes.AccesoManipulacionDatos.model.compositePK.EmpleadoSeccionId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(EmpleadoSeccionId.class)
public class EmpleadoSeccion {
    @Id
    private Long id_empleado;

    @Id
    private Long id_seccion;

    @ManyToOne
    @MapsId("id_empleado")
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @MapsId("id_seccion")
    @JoinColumn(name = "id_seccion")
    private Seccion seccion;
}
