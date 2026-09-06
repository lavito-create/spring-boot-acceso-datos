package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.EmpleadoRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.HistoricoEmpleadoRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Empleado;
import com.dwes.AccesoManipulacionDatos.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Crear empleado
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Listar empleados
    public List<EmpleadoRequestDTO> listarTodos() {
        List<Empleado> empleados= (List<Empleado>) empleadoRepository.findAll();
        return empleados.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    // Listar todos con salarios
    public HistoricoEmpleadoRequestDTO listarTodosConSalario() {
        List<Empleado> listaEmpleados = (List<Empleado>) empleadoRepository.findAll();

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double medio = 0;
        double totalSalarios=0;

        for (Empleado e: listaEmpleados) {
            totalSalarios+=e.getSalario();
           if (e.getSalario() > max) {
               max = e.getSalario();
           }
           if (e.getSalario() < min) {
               min = e.getSalario();
           }
        }
        medio = totalSalarios / listaEmpleados.size();
        return HistoricoEmpleadoRequestDTO.builder()
                .salarioMaximo(max)
                .salarioMinimo(min)
                .salarioMedio(medio)
                .build();
    }

    // Listado empleado cuyo apellido sea Llano
    public List<EmpleadoRequestDTO> listarLlano() {
        List<Empleado> listaEmpleado = (List<Empleado>) empleadoRepository.findAll();
        List<EmpleadoRequestDTO> listaLlano = new ArrayList<>();
        for (Empleado e: listaEmpleado) {
            if (e.getApellido().equalsIgnoreCase("Llano")) {
                listaLlano.add(mapToRequestDTO(e));
            }
        }
        return listaLlano;
    }

    // Eliminar empleado
    public void deleteEmpleadoById(Long idempleado) {
        empleadoRepository.deleteById(idempleado);
    }

    // Mapeo dto
    private EmpleadoRequestDTO mapToRequestDTO(Empleado empleado) {
        return EmpleadoRequestDTO.builder()
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .correo(empleado.getCorreo())
                .build();
    }

    // Mapeo historico empleado
    private HistoricoEmpleadoRequestDTO historicoEmpleadoRequestDTO(Empleado empleado) {
        return HistoricoEmpleadoRequestDTO.builder()
                .salarioMedio(empleado.getSalario())
                .salarioMaximo(empleado.getSalario())
                .salarioMinimo(empleado.getSalario())
                .build();
    }
}
