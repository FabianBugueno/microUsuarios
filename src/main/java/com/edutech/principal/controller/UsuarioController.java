package com.edutech.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.principal.model.Usuario;
import com.edutech.principal.model.dto.UsuarioDto;
import com.edutech.principal.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @Operation(summary = "Obtener un usuario por correo")
    @GetMapping("/obtenerUsuario/{correo}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String correo) {
        Usuario usuario = usuarioService.obtenerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener un usuario DTO por ID")
    @GetMapping("/obtenerUsuarioDto/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDto(@PathVariable int id) {
        if (usuarioService.obtenerUsuarioDto(id) != null) {
            return ResponseEntity.ok(usuarioService.obtenerUsuarioDto(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Modificar usuario por ID")
    @PutMapping("/modificarUsuario/{id}")
    public ResponseEntity<String> modificarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        String respuesta = usuarioService.modificarUsuario(id, usuario);
        if (respuesta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Usuario modificado correctamente");
    }

    @Operation(summary = "Eliminar usuario por ID")
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        String respuesta = usuarioService.eliminarUsuario(id);
        if (respuesta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }
}
