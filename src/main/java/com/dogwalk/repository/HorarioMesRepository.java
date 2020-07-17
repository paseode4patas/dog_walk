package com.dogwalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwalk.entity.HorarioMesEntity;

public interface HorarioMesRepository extends JpaRepository<HorarioMesEntity, Integer> {

	List<HorarioMesEntity> findHorarioMensualByPaseadorIdAndEstado(Integer idPaseador, Boolean estado);

	List<HorarioMesEntity> findHorarioMensualByMesAndAnioAndEstadoOrderByFechaAscPaseadorIdAsc(String mes,
			String anio, Boolean estado);

}
