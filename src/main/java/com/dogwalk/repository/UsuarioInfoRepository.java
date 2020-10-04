package com.dogwalk.repository;

import com.dogwalk.entity.UsuarioInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioInfoRepository extends JpaRepository<UsuarioInfoEntity, Integer> {

	List<UsuarioInfoEntity> findPaseadorByEstado(Boolean estado);

}
