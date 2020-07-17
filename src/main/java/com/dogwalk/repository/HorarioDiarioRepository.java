package com.dogwalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwalk.entity.HorarioDiarioEntity;

public interface HorarioDiarioRepository extends JpaRepository<HorarioDiarioEntity, Integer> {

	List<HorarioDiarioEntity> findHorarioByPaseadorIdAndEstado(Integer idPaseador, Boolean estado);

	List<HorarioDiarioEntity> findHorarioByPaseadorIdAndFechaAndEstado(Integer idPaseador, String fechaPaseo,
			Boolean estado);

}
