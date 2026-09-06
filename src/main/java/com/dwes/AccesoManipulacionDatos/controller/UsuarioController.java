package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.UsuarioRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Usuario;
import com.dwes.AccesoManipulacionDatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear usuario
    @PostMapping("/crearUsuario")
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    // Actualizar datos del usuario
    @PutMapping("/actualizarUsuario/{id}")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario, @PathVariable("id") Long idUsuario) {
        return usuarioService.updateUsuario(usuario, idUsuario);
    }

    // Listar todos los usuariosDTO
    @GetMapping("/empledos/listarTodos")
    public List<UsuarioRequestDTO> listarTodos() {
        return usuarioService.listarTodos();
    }

    // Eliminar usuario por id
    @DeleteMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioService.eliminarUsuarioById(idUsuario);
        return "Usuario eliminado";
    }

}
