package com.ms_gestor_de_usuario.principal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.ms_gestor_de_usuario.principal.models.Usuario;
import com.ms_gestor_de_usuario.principal.models.dto.UsuarioDto;
import com.ms_gestor_de_usuario.principal.models.entity.UsuarioEntity;
import com.ms_gestor_de_usuario.principal.repository.UsuarioRepository;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //Obtener usuario por correo
    public Usuario traerUsuario(String correo){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null){
                Usuario usuarioNuevo = new Usuario(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getRut(),
                    usuario.getDireccion(),
                    usuario.getCorreo(),
                    usuario.getRol(),
                    usuario.getContrasena()
                );
                return usuarioNuevo;

            }
            return null;


        }
        catch (Exception e){
            return null;
        }
        

    }

    //Agregar usuario 
    public String agregaUsuario(Usuario user) {
        try {
            boolean estado = usuarioRepository.existsByCorreo(user.getCorreo());
            if (!estado) {
                UsuarioEntity nuevoUsuario = new UsuarioEntity();
                nuevoUsuario.setNombre(user.getNombre());
                nuevoUsuario.setApellidoPaterno(user.getApellidoPaterno());
                nuevoUsuario.setApellidoMaterno(user.getApellidoMaterno());
                nuevoUsuario.setRut(user.getRut());
                nuevoUsuario.setDireccion(user.getDireccion());
                nuevoUsuario.setCorreo(user.getCorreo());
                nuevoUsuario.setCorreo(user.getCorreo());
                nuevoUsuario.setRol(user.getRol());
                nuevoUsuario.setContrasena(user.getContrasena());
                usuarioRepository.save(nuevoUsuario);
                return "Usuario agregado correctamente";
            }
            return "El usuario ya existe";
        } catch (ObjectOptimisticLockingFailureException e) {
            return "Error de concurrencia: " + e.getMessage();
        } catch (Exception e) {
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }

    //Borrar usuario por ID
    public String borrarUsuario(int id) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return "Usuario borrado correctamente";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al borrar usuario: PRUEBVAAA asdsad" + e.getMessage();
        }
    }

    //Listar todos los usuarios
    public List<UsuarioEntity> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    //Actualizar usuario
    public String actualizarUsuario(int id, Usuario user) {
        try {
            UsuarioEntity usuarioActualizado = usuarioRepository.findUsuarioById(user.getId());
            if (usuarioActualizado != null) {
                usuarioActualizado.setNombre(user.getNombre());
                usuarioActualizado.setApellidoPaterno(user.getApellidoPaterno());
                usuarioActualizado.setApellidoMaterno(user.getApellidoMaterno());
                usuarioActualizado.setRut(user.getRut());
                usuarioActualizado.setDireccion(user.getDireccion());
                usuarioActualizado.setCorreo(user.getCorreo());
                usuarioActualizado.setRol(user.getRol());
                usuarioActualizado.setContrasena(user.getContrasena());
                usuarioRepository.save(usuarioActualizado);
                return "Usuario actualizado correctamente";
            } else {
                return "Usuario no encontrado";
            }
        } catch (Exception e) {
            return "Error al actualizar usuario";
        }
    }


    //DTO´s
    public ResponseEntity<UsuarioDto> obtenerUserDto( String correo){
        Boolean estado = usuarioRepository.existsByCorreo(correo);
        if (estado){
            UsuarioEntity nuevoUsuario = usuarioRepository.findByCorreo(correo);
            UsuarioDto usuarioResponse = new UsuarioDto(
                nuevoUsuario.getRut(),
                nuevoUsuario.getNombre(),
                nuevoUsuario.getApellidoPaterno(),
                nuevoUsuario.getCorreo()
            );
            return ResponseEntity.ok(usuarioResponse);


        }
        return ResponseEntity.notFound().build();

    }

    public UsuarioDto obtenerUsuarioId(int id){
        try{
            Boolean estado = usuarioRepository.existsById(id);
            if (estado){
                UsuarioEntity nuevoUsuario = usuarioRepository.findUsuarioById(id);
                UsuarioDto responseUsuario = new UsuarioDto(
                    nuevoUsuario.getRut(),
                    nuevoUsuario.getNombre(),
                    nuevoUsuario.getApellidoPaterno(),
                    nuevoUsuario.getCorreo()
                );
                return responseUsuario;
            }
            return null ;
        }
        catch(Exception e){
            
            return null;
        }


    }

}
