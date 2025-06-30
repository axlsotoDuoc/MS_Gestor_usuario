package com.ms_gestor_de_usuario.principal.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String direccion;
    private String correo;
    private String rol;
    private String contrasena;
}
