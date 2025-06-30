package com.ms_gestor_de_usuario.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms_gestor_de_usuario.principal.models.Usuario;
import com.ms_gestor_de_usuario.principal.models.dto.UsuarioDto;
import com.ms_gestor_de_usuario.principal.models.entity.UsuarioEntity;
import com.ms_gestor_de_usuario.principal.repository.UsuarioRepository;
import com.ms_gestor_de_usuario.principal.service.UserService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class UserController {

    @Autowired
    private UserService usuarioservice;
    UserService accionesUser = new UserService();

    UserController(UsuarioRepository usuarioRepository) {
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        return ResponseEntity.ok(usuarioservice.listarUsuarios());
    }
    

    @Operation(summary = "Este endpoint permite obtener un usuario por correo")
    @GetMapping("/usuarios/{correo}")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable String correo) {
        Usuario usuario = usuarioservice.traerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }


    @Operation(summary = "Este endpoint permite agregar un usuario")
    @PostMapping("/agregarUsuario")
    public ResponseEntity<String> agregarUsuario(@RequestBody Usuario usuario) {
        String resultado = usuarioservice.agregaUsuario(usuario);
        if (resultado.equals("Usuario agregado correctamente")) {
            return ResponseEntity.status(201).body(resultado); // 201 Created
        } else if (resultado.equals("El usuario ya está agregado")) {
            return ResponseEntity.status(409).body(resultado); // 409 Conflict
        }
        return ResponseEntity.badRequest().body(resultado); // 400 Bad Request
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @DeleteMapping("/borrarUsuario/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable int id) {
        String resultado = usuarioservice.borrarUsuario(id);
        if (resultado.equals("Usuario eliminado correctamente")) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.status(404).body(resultado); // 404 Not Found
    }
    

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/actualizarUsuario")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
        String resultado = usuarioservice.actualizarUsuario(0, usuario);
        if (resultado.equals("Usuario actualizado correctamente")) {
            return ResponseEntity.ok(resultado); // 200 OK
        } else if (resultado.equals("Usuario no encontrado")) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.badRequest().body(resultado); // 400 Bad Request
    }


    //DTO´s
    @Operation(summary = "Este endpoint es similar al 'traerUsuario' ya que retorna el usuario asociado al correo que le entreguemosm como parámetro con la diferencia de que únicamente nos mostrará los valores que hayamos declarado en el DTO.")
    @GetMapping("/usuariodto/{correo}")
    public ResponseEntity<UsuarioDto> obtenerUserDto(@PathVariable String correo){
        return usuarioservice.obtenerUserDto(correo);

    }

    @Operation(summary = "Este endpoint retorna el usuario asociado al ID que le entreguemos como parámetro y únicamente nos mostrará los valores que hayamos declarado en el DTO")
    @GetMapping("/usuariodtoid/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioId(@PathVariable int id) {
        UsuarioDto usuarioDto = usuarioservice.obtenerUsuarioId(id);
        if (usuarioDto != null) {
            return ResponseEntity.ok(usuarioDto); 
        }
        return ResponseEntity.notFound().build(); 
    }
}
