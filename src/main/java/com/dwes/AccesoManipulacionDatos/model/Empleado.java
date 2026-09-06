package com.dwes.AccesoManipulacionDatos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Este campo debe ser obligatorio")
    private String nombre;
    private String apellido;

    @Email
    private String correo;

    @Min(value = 600, message = "El salario mínimo es 600€")
    private Double salario;

    @OneToMany(mappedBy = "empleado")
    private Set<EmpleadoSeccion> empleadoSecciones;
}
