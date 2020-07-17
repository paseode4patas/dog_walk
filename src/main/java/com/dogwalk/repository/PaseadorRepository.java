package com.dogwalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwalk.entity.PaseadorEntity;

public interface PaseadorRepository extends JpaRepository<PaseadorEntity, Integer> {

	List<PaseadorEntity> findPaseadorByEstado(Boolean estado);

}
