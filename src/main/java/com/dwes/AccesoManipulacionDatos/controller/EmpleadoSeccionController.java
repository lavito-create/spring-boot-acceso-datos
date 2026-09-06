package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.EmpleadoRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.SeccionRequestDTO;
import com.dwes.AccesoManipulacionDatos.service.EmpleadoSeccionService;
import com.dwes.AccesoManipulacionDatos.service.EmpleadoService;
import com.dwes.AccesoManipulacionDatos.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadoSeccionController {
    private final EmpleadoService empleadoService;
    private final SeccionService seccionservice;
    private final EmpleadoSeccionService empleadoSeccionService;

    @Autowired
    public EmpleadoSeccionController(EmpleadoService empleadoServices, SeccionService service, EmpleadoService empleadoService, SeccionService seccionservice, EmpleadoSeccionService empleadoSeccionService) {
        this.empleadoService = empleadoService;
        this.seccionservice = seccionservice;
        this.empleadoSeccionService = empleadoSeccionService;
    }

    @PostMapping("/asignarEmpleadoASeccion")
    public String asignarEmpleadoASeccion(@RequestParam Long empleadoId, @RequestParam Long seccionId) {
        try {
            empleadoSeccionService.asignarEmpleadoASeccion(empleadoId, seccionId);
            return "Empleado asignado a sección correctamente";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/empleadosSeccion")
    public List<EmpleadoRequestDTO> obtenerEmpleadosSeccion(Long idSeccion) {
        return empleadoSeccionService.getEmpleadoSeccion(idSeccion);
    }

    @GetMapping("/seccionEmpleados")
    public List<SeccionRequestDTO> obtenerSeccionesEmpleado(Long idEmpleado) {
        return empleadoSeccionService.getSeccionEmpleado(idEmpleado);
    }

    @PutMapping("/empleados/{empleadoId}/cambiar-seccion")
    public String cambiarSeccionEmpleado(@PathVariable Long empleadoId,
                                         @RequestParam Long seccionIdAntigua,
                                         @RequestParam Long seccionIdNueva) {
        empleadoSeccionService.cambiarSeccionEmpleado(empleadoId, seccionIdAntigua, seccionIdNueva);
        return "Se ha actualizado la sección del empleado";
    }

    @DeleteMapping("/empleados/{empleadoId}/secciones/{seccionId}")
    public String borrarEmpleadoSeccion(@PathVariable Long empleadoId, @PathVariable Long seccionId) {
        empleadoSeccionService.borrarEmpleadoSeccion(empleadoId, seccionId);
        return "Se ha eliminado el empleado se la sección";
    }

    @DeleteMapping("/empleados/{empleadoId}/secciones/{seccionId}/con-is-present")
    public String borrarEmpleadoSeccionConIsPresent(@PathVariable Long empleadoId, @PathVariable Long seccionId) {
        boolean eliminado = empleadoSeccionService.borrarEmpleadoSeccionIsPresent(empleadoId, seccionId);

        if (eliminado) {
            return "Se ha elminado a relación del empleado" + empleadoId + " con la sección " + seccionId;
        } else {
            return "No se encontró la relación del empleado " + empleadoId + " y la sección " + seccionId;
        }
    }

}
