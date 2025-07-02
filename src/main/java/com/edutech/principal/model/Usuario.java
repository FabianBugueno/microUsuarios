package com.edutech.principal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
}
