package com.ms_gestor_de_usuario.principal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

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
