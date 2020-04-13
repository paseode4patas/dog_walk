package com.dogwalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dogwalk.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	UsuarioEntity findUsuarioById(Integer idUsuario);

	UsuarioEntity findByNombreUsuario(String usuario);

	UsuarioEntity findByNombreUsuarioAndContrasena(String usuario, String contrasena);

}
