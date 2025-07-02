package com.edutech.principal.controller;

import com.edutech.principal.model.Usuario;
import com.edutech.principal.model.dto.UsuarioDto;
import com.edutech.principal.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Crear usuario - éxito")
    void crearUsuario_ok() throws Exception {
        Usuario usuario = new Usuario(1, "Juan", "Pérez", "juan@mail.com", "123");
        when(usuarioService.crearUsuario(any())).thenReturn("Usuario creado correctamente");

        mockMvc.perform(post("/crearUsuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario creado correctamente"));
    }

    @Test
    @DisplayName("Obtener usuario por correo - encontrado")
    void obtenerUsuario_existente() throws Exception {
        Usuario usuario = new Usuario(1, "Ana", "López", "ana@mail.com", "clave");
        when(usuarioService.obtenerUsuario("ana@mail.com")).thenReturn(usuario);

        mockMvc.perform(get("/obtenerUsuario/ana@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"));
    }

    @Test
    @DisplayName("Obtener usuario por correo - no encontrado")
    void obtenerUsuario_noExistente() throws Exception {
        when(usuarioService.obtenerUsuario("no@existe.com")).thenReturn(null);

        mockMvc.perform(get("/obtenerUsuario/no@existe.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Obtener usuario DTO - éxito")
    void obtenerUsuarioDto_ok() throws Exception {
        UsuarioDto dto = new UsuarioDto("Carlos", "Ramírez", "carlos@mail.com");
        when(usuarioService.obtenerUsuarioDto(1)).thenReturn(dto);

        mockMvc.perform(get("/obtenerUsuarioDto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("carlos@mail.com"));
    }

    @Test
    @DisplayName("Modificar usuario - éxito")
    void modificarUsuario_ok() throws Exception {
        Usuario usuario = new Usuario(1, "Lucía", "Martínez", "lucia@mail.com", "abc");
        when(usuarioService.modificarUsuario(eq(1), any())).thenReturn("Usuario modificado correctamente");

        mockMvc.perform(put("/modificarUsuario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario modificado correctamente"));
    }

    @Test
    @DisplayName("Modificar usuario - no encontrado")
    void modificarUsuario_noExiste() throws Exception {
        when(usuarioService.modificarUsuario(eq(999), any())).thenReturn(null);

        mockMvc.perform(put("/modificarUsuario/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Usuario())))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Eliminar usuario - éxito")
    void eliminarUsuario_ok() throws Exception {
        when(usuarioService.eliminarUsuario(1)).thenReturn("Usuario eliminado");

        mockMvc.perform(delete("/eliminarUsuario/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado"));
    }

    @Test
    @DisplayName("Eliminar usuario - no encontrado")
    void eliminarUsuario_noExiste() throws Exception {
        when(usuarioService.eliminarUsuario(404)).thenReturn(null);

        mockMvc.perform(delete("/eliminarUsuario/404"))
                .andExpect(status().isNotFound());
    }
}
