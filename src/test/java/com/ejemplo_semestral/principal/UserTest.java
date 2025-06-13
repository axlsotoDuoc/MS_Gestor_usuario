package com.ejemplo_semestral.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ms_gestor_de_usuario.principal.models.Usuario;
import com.ms_gestor_de_usuario.principal.models.entity.UsuarioEntity;
import com.ms_gestor_de_usuario.principal.repository.UsuarioRepository;
import com.ms_gestor_de_usuario.principal.service.UserService;

public class UserTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UserService userService;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(1, "Axel", "Soto", "Valdivia", "21.266.058-2", "Serrano 1417", "ax.sotov@duocuc.cl", "Administrador", "axelaxel");
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1);
        usuarioEntity.setNombre("Axel");
        usuarioEntity.setApellidoPaterno("Soto");
        usuarioEntity.setApellidoMaterno("Valdivia");
        usuarioEntity.setRut("21.266.058-2");
        usuarioEntity.setDireccion("Serrano 1417");
        usuarioEntity.setCorreo("ax.sotov@duocuc.cl");
        usuarioEntity.setRol("Administrador");
        usuarioEntity.setContrasena("axelaxel");
    }

    @Test
    public void testAgregarUsuario_nuevo(){
        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        String result = userService.agregaUsuario(usuario);
        assertEquals("Usuario agregado correctamente", result);
    }

    @Test
    public void testAgregarUsuario_existe(){
        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(true);

        String result = userService.agregaUsuario(usuario);
        assertEquals("El usuario ya existe", result);
    }

    @Test
    public void testTraerUsuarioPorCorreo(){
        when(usuarioRepository.findByCorreo("ax.sotov@duocuc.cl")).thenReturn(usuarioEntity);
        Usuario result = userService.traerUsuario("ax.sotov@duocuc.cl");
        assertNotNull(result);
        assertEquals("Axel", result.getNombre());
    }

    @Test
    public void testTraerUsuarioNoExiste(){
        when(usuarioRepository.findByCorreo("noexiste@gmail.com")).thenReturn(null);
        Usuario result = userService.traerUsuario("noexiste@gmail.com");
        assertNull(result);
    }

    @Test
    public void actualizarUsuario_existe(){
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario nuevo =  new Usuario(1, "Axel", "Soto", "Valdivia", "21.266.058-2", "Serrano 1417", "ax.sotov@duocuc.cl", "Administrador", "axelaxel");;
        String result = userService.actualizarUsuario(1, nuevo);

        assertEquals("Usuario actualizado correctamente", result);
    }

    @Test
    public void borrarUsuario(){
        when(usuarioRepository.existsById(1)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1);
        String result = userService.borrarUsuario(1);

        assertEquals("Usuario borrado correctamente", result);
    }
}
