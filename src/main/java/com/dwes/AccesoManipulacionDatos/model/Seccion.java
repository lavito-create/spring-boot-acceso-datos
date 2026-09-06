package com.dwes.AccesoManipulacionDatos.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.crypto.Mac;
import java.util.Set;

@Entity
@Data
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "seccion")
    private Set<EmpleadoSeccion> empleadoSecciones;
}
