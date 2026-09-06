package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.model.Departamento;
import com.dwes.AccesoManipulacionDatos.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService=departamentoService;
    }

    // Operación CREATE
    //@RequestBody indica que el cuerpo de la solicitud HTTP
    // se debe convertir en un objeto Departamento
    // @Valid indica que se debe realizar validación
    // de este objeto según las anotaciones de validación
    // que puedan estar presentes en la clase Departamento
    @PostMapping("/departamentos")
    public Departamento saveDepartment(@Valid @RequestBody Departamento departamento) {
        return departamentoService.saveDepartment(departamento);
    }


    // Operación READ
    @GetMapping("/departamentos")
    public List<Departamento> fetchDepartmentList() {
        return departamentoService.fetchDepartamentoList();
    }

    // Operación UPDATE
    //@PutMapping("/departamentos/{id}"): Indica que
    // este método manejará solicitudes PUT enviadas
    // a la ruta "/departamentos/{id}".
    // El {id} en la ruta indica que se espera un identificador único
    // como parte de la URL para identificar el departamento que se
    // actualizará.
    //@RequestBody: Indica que el cuerpo de la solicitud HTTP se
    // convertirá en un objeto Departamento.
    //@PathVariable("id"): Indica que el valor del id en la URL
    // será mapeado al parámetro iddepartamento de tipo Long.
    @PutMapping("/departamentos/{id}")
    public Departamento updateDepartment(@RequestBody Departamento departamento, @PathVariable("id") Long iddepartamento) {
        return departamentoService.updateDepartamento(departamento, iddepartamento);
    }

    // Operación DELETE
    //@DeleteMapping("/departamentos/{id}"): Indica que este método manejará
    // solicitudes DELETE enviadas a la ruta "/departamentos/{id}".
    // El {id} en la ruta indica que se espera un identificador único
    // como parte de la URL para identificar el departamento que se eliminará.
    // @PathVariable("id") Long iddepartamento: @PathVariable indica que
    // el valor del id en la URL será mapeado al parámetro iddepartamento
    // de tipo Long.
    @DeleteMapping("/departamentos/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long iddepartamento) {
        departamentoService.deleteDepartmentById(iddepartamento);
        return "Deleted Successfully";
    }

}
