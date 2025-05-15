package com.gestor_usuario.ms_gestor_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestor_usuario.ms_gestor_usuario.model.entity.usuarioEntity;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioEntity, Integer>{

    usuarioEntity findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    void deleteByCorreo(String correo);

}
