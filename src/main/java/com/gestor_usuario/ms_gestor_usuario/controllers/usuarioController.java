package com.gestor_usuario.ms_gestor_usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestor_usuario.ms_gestor_usuario.model.usuario;
import com.gestor_usuario.ms_gestor_usuario.service.usuarioService;

@RestController
public class usuarioController {
    @Autowired
    private usuarioService usuarioService;

    @PostMapping("/crearUsuario")
    //ReponseEntiy <--- Responder según acción o resultado
    // 404 ---> No se encuentra el recurso
    // 202 ---> Ok
    public ResponseEntity<String> obtenerUsuario(@RequestBody usuario user){
        return ResponseEntity.ok(usuarioService.crearUsuario(user));
    }

    @GetMapping("/obtenerUsuario/(correo)")
    public ResponseEntity<usuario> obtenerUsuario(@PathVariable String correo){
        usuario user = usuarioService.obtenerUsuario(correo);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    

    
}
