package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.EmpleadoRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.SeccionRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Empleado;
import com.dwes.AccesoManipulacionDatos.model.EmpleadoSeccion;
import com.dwes.AccesoManipulacionDatos.model.Seccion;
import com.dwes.AccesoManipulacionDatos.repository.EmpleadoRepository;
import com.dwes.AccesoManipulacionDatos.repository.EmpleadoSeccionRepository;
import com.dwes.AccesoManipulacionDatos.repository.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpleadoSeccionService {
    @Autowired
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    private final SeccionRepository seccionRepository;

    @Autowired
    private final EmpleadoSeccionRepository empleadoSeccionRepository;

    @Autowired
    public EmpleadoSeccionService(EmpleadoRepository empleadoRepository, SeccionRepository seccionRepository, EmpleadoSeccionRepository empleadoSeccionRepository) {
        this.empleadoRepository = empleadoRepository;
        this.seccionRepository = seccionRepository;
        this.empleadoSeccionRepository = empleadoSeccionRepository;
    }

    @Transactional
    public void asignarEmpleadoASeccion(Long empleadoId, Long seccionId) {
        Optional<Empleado> optionalEmpleado=empleadoRepository.findById(empleadoId);
        Optional<Seccion> optionalSeccion = seccionRepository.findById(seccionId);

        if (optionalEmpleado.isPresent() && optionalSeccion.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            Seccion seccion = optionalSeccion.get();

            // Crear una nueva instacia de EmpeladoSeccion
            EmpleadoSeccion empleadoSeccion=new EmpleadoSeccion();

            empleadoSeccion.setEmpleado(empleado);
            empleadoSeccion.setSeccion(seccion);
            empleadoSeccion.setId_empleado(empleado.getId());
            empleadoSeccion.setId_seccion(seccion.getId());

            empleadoSeccionRepository.save(empleadoSeccion);
        } else {
            // Manejar el caso en que no se encuentre el empleado y la sección
            throw  new IllegalArgumentException("Empleado o sección no encontrados");
        }
    }

    // Sacar todos los empleados de una seccion
    // Siempre que sea sacar información sobre los empleados o secciones por su id se utiliza la anotación @Transational(readonly = true)
    @Transactional(readOnly = true)
    /* Utilizamos list en vez de set */
    public List<EmpleadoRequestDTO> getEmpleadoSeccion(Long idSeccion) {
        Set<EmpleadoSeccion> empleadoSecciones = empleadoSeccionRepository.findBySeccionId(idSeccion);
        // Crear un conjunto para almacenar empleados (Utilizamos List en vez de Set)
        List<Empleado> empleados = new ArrayList<>();

        // Iterar sobre la colección de EmpleadoSeccion y obtener los empleados asociados
        for (EmpleadoSeccion empleadoSeccion: empleadoSecciones) {
            empleados.add(empleadoSeccion.getEmpleado());
        }
        return empleados.stream()
                .map(this::mapToRequestDTOEmpleado)
                .collect(Collectors.toList());
    }

    // Sacar todas las secciones de un empleado
    // Siempre que sea sacar información sobre los empleados o secciones por su id se utiliza la anotación @Transational(readOnly = true)
    @Transactional(readOnly = true)
    /* Utilizamos list en vez de set */
    public List<SeccionRequestDTO> getSeccionEmpleado(Long idEmpleado) {
        Set<EmpleadoSeccion> seccionEmpleados = empleadoSeccionRepository.findByEmpleadoId(idEmpleado);
        List<Seccion> secciones = new ArrayList<>();

        for (EmpleadoSeccion empleadoSeccion: seccionEmpleados) {
            secciones.add(empleadoSeccion.getSeccion());
        }
        return secciones.stream()
                .map(this::mapToRequestDTOSeccion)
                .collect(Collectors.toList());
    }

    // Mapeo DTO Empleado
    private EmpleadoRequestDTO mapToRequestDTOEmpleado(Empleado empleado) {
        return EmpleadoRequestDTO.builder()
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .correo(empleado.getCorreo())
                .build();
    }

    // Mapeo DTO Seccion
    private SeccionRequestDTO mapToRequestDTOSeccion(Seccion seccion) {
        return SeccionRequestDTO.builder()
                .nombre(seccion.getNombre())
                .build();
    }

    // Cambiar de sección a un empleado
    @Transactional
    public void cambiarSeccionEmpleado(Long empleadoId, Long seccionIdAntigua, Long seccionIdNueva) {
        // Buscar si existe la relación actual
        Optional<EmpleadoSeccion> optionalEmpleadoSeccion = empleadoSeccionRepository.findBySeccionIdAndEmpleadoId(seccionIdAntigua, empleadoId);

        if (optionalEmpleadoSeccion.isPresent()) {
            EmpleadoSeccion empleadoSeccionExistente = optionalEmpleadoSeccion.get();

            // Eliminar la sección antigua
            empleadoSeccionRepository.delete(empleadoSeccionExistente);
        }

        // Buscar el empleado
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(empleadoId);

        if (optionalEmpleadoSeccion.isPresent()) {
            Empleado empleado = optionalEmpleado.get();

            // Buscar la nueva sección
            Optional<Seccion> optionalSeccionNueva = seccionRepository.findById(seccionIdNueva);

            if (optionalSeccionNueva.isPresent()) {
                Seccion seccionNueva = optionalSeccionNueva.get();

                // Crear la nueva relación
                EmpleadoSeccion nuevoEmpleadoSeccion = new EmpleadoSeccion();

                nuevoEmpleadoSeccion.setEmpleado(empleado);
                nuevoEmpleadoSeccion.setSeccion(seccionNueva);
                nuevoEmpleadoSeccion.setId_empleado(empleado.getId());
                nuevoEmpleadoSeccion.setId_seccion(seccionNueva.getId());

                empleadoSeccionRepository.save(nuevoEmpleadoSeccion);
            }
        }
    }

    // Eliminar un empleado de una seccion
    @Transactional
    public void borrarEmpleadoSeccion(Long empleadoId, Long seccionId) {
        empleadoSeccionRepository.deleteByEmpleadoIdAndSeccionId(empleadoId, seccionId);
    }

    // Eliminar Relación utilizando isPresent
    @Transactional
    public boolean borrarEmpleadoSeccionIsPresent(Long empleadoId, Long seccionId) {
        // Buscar si la relación existe
        var relacion = empleadoSeccionRepository.findBySeccionIdAndEmpleadoId(empleadoId, seccionId);

        if (relacion.isPresent()) {
            empleadoSeccionRepository.delete(relacion.get());
            return true; // Se eliminó
        } else {
            return false; // No se encontró la relación
        }
    }
}
