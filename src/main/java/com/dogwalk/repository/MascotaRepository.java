package com.dogwalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwalk.entity.MascotaEntity;

public interface MascotaRepository extends JpaRepository<MascotaEntity, Integer> {

	MascotaEntity findMascotaById(Integer idUsuario);

	List<MascotaEntity> findMascotaByUsuarioIdAndEstado(Integer idUsuario, Boolean estado);

}
