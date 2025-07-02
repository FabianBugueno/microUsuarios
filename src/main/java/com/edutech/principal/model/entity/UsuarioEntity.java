package com.edutech.principal.model.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@EntityScan
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombre")
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;  

}
