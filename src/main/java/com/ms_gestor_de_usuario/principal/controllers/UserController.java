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
    
    @Operation(summary = "Obtener un usuario por correo.")
    @GetMapping("/usuarios/{correo}")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable String correo){
        return ResponseEntity.ok(usuarioservice.traerUsuario(correo));
    }


    @Operation(summary = "Agregar un nuevo usuario.")
    @PostMapping("/usuarios")
    public ResponseEntity<String> agregarUsuario (@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioservice.agregaUsuario(usuario));

    }

    @Operation(summary = "Eliminar un usuario por ID")
    @DeleteMapping("/borrarUsuario/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable int id) {
        return ResponseEntity.ok(usuarioservice.borrarUsuario(id));
    }

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/actualizarUsuario")
    public ResponseEntity<String> actualizarUsuario(@PathVariable int id,@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioservice.actualizarUsuario(id, usuario));
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
