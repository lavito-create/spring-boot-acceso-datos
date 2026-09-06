package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.VueloRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.VueloTotalRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Vuelo;
import com.dwes.AccesoManipulacionDatos.service.VueloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VueloController {
    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping("/crearVuelo")
    public Vuelo saveVuelo(@Valid @RequestBody Vuelo vuelo) {
        return vueloService.saveVuelo(vuelo);
    }

    @GetMapping("/listarVuelos")
    public List<VueloRequestDTO> listarVuelos() {
        return vueloService.listarTodos();
    }

    @GetMapping("/listarTotalVuelos")
    public List<VueloTotalRequestDTO> listarVuelosConTotal() {
        return vueloService.listarVuelosConTotal();
    }

    @GetMapping("/listarVuelosConTotalSinStream")
    public List<VueloTotalRequestDTO> listaVuelosConTotalSinStream() {
        return vueloService.listarVueloConTotalSinStream();
    }

    @PutMapping("/vuelos/{id}")
    public Vuelo updateVuelo(@RequestBody Vuelo vuelo, @PathVariable("id") Long idVuelo) {
        return vueloService.updateVuelo(vuelo,idVuelo);
    }

    @DeleteMapping("/borrarVuelo/{id}")
    public String eliminarVuelo(@PathVariable("id") Long idVuelo) {
        return vueloService.eliminarVueloByExistsId(idVuelo);
    }
}
