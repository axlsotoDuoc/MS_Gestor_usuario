package com.ms_gestor_de_usuario.principal.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private String rut, nombre, apellidoPaterno, correo;
}
