package com.dogwalk.repository;

import com.dogwalk.entity.HorarioMesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioMesRepository extends JpaRepository<HorarioMesEntity, Integer> {

	List<HorarioMesEntity> findHorarioMensualByPaseadorIdAndEstado(Integer idPaseador, Boolean estado);

	List<HorarioMesEntity> findHorarioMensualByMesAndAnioAndEstadoOrderByFechaAscPaseadorIdAsc(String mes,
	                                                                                           String anio, Boolean estado);

	List<HorarioMesEntity> findByPaseadorIdAndMesAndAnio(Integer idPaseador, String mes, String anio);
}
