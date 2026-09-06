package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.EmpleadoRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.HistoricoEmpleadoRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Empleado;
import com.dwes.AccesoManipulacionDatos.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/crearEmpleado")
    public Empleado saveEmpleado(@Valid @RequestBody Empleado empleado) {
        return empleadoService.crearEmpleado(empleado);
    }

    @GetMapping("/empleado/listarTodos")
    public List<EmpleadoRequestDTO> Empleados() {
        return empleadoService.listarTodos();
    }

    @GetMapping("/listarTodosConSalario")
    public HistoricoEmpleadoRequestDTO listarTodosSalario() {
        return empleadoService.listarTodosConSalario();
    }

    @GetMapping("/listarApellidoLlano")
    public List<EmpleadoRequestDTO> listarTodosLlano() {
        return empleadoService.listarLlano();
    }

    @DeleteMapping("/empleado/{id}")
    public String deleteEmpleadoById(@PathVariable("id") Long idempleado) {
        empleadoService.deleteEmpleadoById(idempleado);
        return "Deleted Successfully";
    }
}
