package com.dogwalk.repository;

import com.dogwalk.entity.HorarioDiarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioDiarioRepository extends JpaRepository<HorarioDiarioEntity, Integer> {

	List<HorarioDiarioEntity> findHorarioByPaseadorIdAndEstado(Integer idPaseador, Boolean estado);

	List<HorarioDiarioEntity> findHorarioByPaseadorIdAndFechaAndEstado(Integer idPaseador, String fechaPaseo,
			Boolean estado);

	List<HorarioDiarioEntity> findHorarioByPaseadorIdAndFecha(Integer idPaseador, String fechaPaseo);

}
