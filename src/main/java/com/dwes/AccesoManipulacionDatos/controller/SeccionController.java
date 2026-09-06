package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.SeccionRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Seccion;
import com.dwes.AccesoManipulacionDatos.service.SeccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeccionController {
    private final SeccionService seccionService;

    @Autowired
    public SeccionController(SeccionService seccionService) {
        this.seccionService = seccionService;
    }

    @PostMapping("/crearSeccion")
    public Seccion crearSeccion(@Valid @RequestBody Seccion seccion) {
        return seccionService.saveSeccion(seccion);
    }

    @GetMapping("/seccion/listarTodas")
    public List<SeccionRequestDTO> listarTodas() {
        return seccionService.listarTodas();
    }

    @DeleteMapping("/seccion/{id}")
    public String deleteSeccionById(@PathVariable("id") Long idseccion) {
        seccionService.deleteSeccionById(idseccion);
        return "Deleted Successfully";
    }
}
