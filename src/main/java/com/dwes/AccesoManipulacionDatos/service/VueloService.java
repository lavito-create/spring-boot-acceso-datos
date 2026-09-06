package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.VueloRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.VueloTotalRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Vuelo;
import com.dwes.AccesoManipulacionDatos.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VueloService {
    private final VueloRepository vueloRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    // Crear vuelo
    public Vuelo saveVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    // Listar vuelos
    public List<VueloRequestDTO> listarTodos() {
        return ((List<Vuelo>)vueloRepository.findAll()).stream()
                .map(this::mapToRequestDTO).toList();
    }

    // Listar vuelos con total con STREAM
    public List<VueloTotalRequestDTO> listarVuelosConTotal() {
        List<Vuelo> listaVuelos= (List<Vuelo>) vueloRepository.findAll();
        return listaVuelos.stream()
                .map(this::mapToTotalRequestDTO).toList();
    }

    // Listar vuelos con total sin stream
    public List<VueloTotalRequestDTO> listarVueloConTotalSinStream() {
        List<Vuelo> listaVuelos = (List<Vuelo>) vueloRepository.findAll();
        List<VueloTotalRequestDTO> listaDTO = new ArrayList<>();

        for (Vuelo v: listaVuelos) {
            // Calcular el total con la fórmula: precio * capacidadAsientos / 12 * 1000
            double totalVuelo = v.getPrecio() * v.getCapacidadAsientos() / 12 * 1000;

            // Crear DTO manualmente
            VueloTotalRequestDTO dto = VueloTotalRequestDTO.builder()
                    .id(v.getId())
                    .capacidad(v.getCapacidadAsientos())
                    .precio(v.getPrecio())
                    .total(totalVuelo)
                    .build();

            listaDTO.add(dto);
        }
        return listaDTO;
    }

    // Operacion UPDATE
    public Vuelo updateVuelo(Vuelo vuelo, Long idVuelo) {
        Vuelo vueloDB = vueloRepository.findById(idVuelo).get();

        if (Objects.nonNull(vuelo.getId()) && !"".equalsIgnoreCase(String.valueOf(vuelo.getId()))) {
            vueloDB.setId(vuelo.getId());
        }
        if (Objects.nonNull(vuelo.getOrigen()) && !"".equalsIgnoreCase(vuelo.getOrigen())) {
            vueloDB.setOrigen(vuelo.getOrigen());
        }
        if (Objects.nonNull(vuelo.getDestino()) && !"".equalsIgnoreCase(vuelo.getDestino())) {
            vueloDB.setDestino(vuelo.getDestino());
        }
        if (Objects.nonNull(vuelo.getFechaSalida())) {
            vueloDB.setFechaSalida(vuelo.getFechaSalida());
        }
        if (Objects.nonNull(vuelo.getFechaLlegada())) {
            vueloDB.setFechaLlegada(vuelo.getFechaLlegada());
        }
        if (Objects.nonNull(vuelo.getPrecio()) && !"".equalsIgnoreCase(String.valueOf(vuelo.getPrecio()))) {
            vueloDB.setPrecio(vuelo.getPrecio());
        }
        if (Objects.nonNull(vuelo.getCapacidadAsientos()) && !"".equalsIgnoreCase(String.valueOf(vuelo.getCapacidadAsientos()))) {
            vueloDB.setCapacidadAsientos(vuelo.getCapacidadAsientos());
        }
        return vueloRepository.save(vueloDB);
    }

    private VueloTotalRequestDTO mapToTotalRequestDTO(Vuelo vuelo) {
        return VueloTotalRequestDTO.builder()
                .id(vuelo.getId())
                .capacidad(vuelo.getCapacidadAsientos())
                .precio(vuelo.getPrecio())
                .total(vuelo.getPrecio()* vuelo.getCapacidadAsientos() / 12 * 1000)
                .build();
    }

    private VueloRequestDTO mapToRequestDTO(Vuelo vuelo) {
        return VueloRequestDTO.builder()
                .destino(vuelo.getDestino())
                .origen(vuelo.getOrigen())
                .fechaLlegada(vuelo.getFechaLlegada())
                .fechaSalida(vuelo.getFechaSalida())
                .build();
    }

    // Operación DELETE
    public String eliminarVueloByExistsId(Long idVuelo) {
        if (vueloRepository.existsById(idVuelo)) {
            vueloRepository.deleteById(idVuelo);
            return "Vuelo borrado existosamente";
        } else {
            return "No se ha encontrado el vuelo";
        }
    }
}
