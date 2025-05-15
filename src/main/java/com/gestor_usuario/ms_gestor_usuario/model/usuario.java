package com.gestor_usuario.ms_gestor_usuario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class usuario {
    
    private int id;
    private String
            nombre, 
            apellidoPaterno,     
            apellidoMaterno, 
            rut, 
            direccion,
            correo,
            rol,
            contrasena;
}
