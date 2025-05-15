package com.gestor_usuario.ms_gestor_usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestor_usuario.ms_gestor_usuario.model.usuario;
import com.gestor_usuario.ms_gestor_usuario.model.entity.usuarioEntity;
import com.gestor_usuario.ms_gestor_usuario.repository.usuarioRepository;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuariorepository;

    public String crearUsuario(usuario user){

        try {
            Boolean estado = usuariorepository.existsByCorreo(user.getCorreo());
            if (!estado){
                usuarioEntity usuarioNuevo = new usuarioEntity();
                    usuarioNuevo.setId(user.getId());
                    usuarioNuevo.setNombre(user.getNombre());
                    usuarioNuevo.setApellidoPaterno(user.getApellidoPaterno());
                    usuarioNuevo.setApellidoMaterno(user.getApellidoPaterno());
                    usuarioNuevo.setRut(user.getRut());
                    usuarioNuevo.setDireccion(user.getDireccion());
                    usuarioNuevo.setCorreo(user.getCorreo());
                    usuarioNuevo.setRol(user.getRol());
                    usuarioNuevo.setContrasena(user.getContrasena());
                    usuariorepository.save(usuarioNuevo);
                    return "Usuario creado correctamente";
            }
            return "Ya existe un usuario con este correo electrónico";

        } catch (Exception e) {return "Error al crear el usuario";}
    }

    public usuario obtenerUsuario(String correo){
        try{
            usuarioEntity usuario = usuariorepository.findByCorreo(correo);
            if(usuario != null){
                usuario user = new usuario(
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
                return user;
            }
            return null;
        } catch(Exception e){
            return null;
        }
    }






}
