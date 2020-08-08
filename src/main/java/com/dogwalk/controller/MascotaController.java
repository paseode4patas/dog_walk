package com.dogwalk.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogwalk.dto.MascotaDto;
import com.dogwalk.dto.MensajeDto;
import com.dogwalk.entity.MascotaEntity;
import com.dogwalk.service.MascotaService;
import com.dogwalk.util.Constantes;

@RestController
@RequestMapping("/mascota")
public class MascotaController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MascotaService mascotaService;

	@PostMapping("")
	public ResponseEntity<MascotaDto> crearMascota(@Valid @RequestBody MascotaEntity mascotaEntity) {

		String nombreMetodo = "crearMascota";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		MascotaDto mascotaDto = mascotaService.crearMascota(mascotaEntity);

		if (mascotaDto.getId() != null && mascotaDto.getId() > 0) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<MascotaDto> responseEntity = new ResponseEntity<>(mascotaDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<List<MascotaDto>> listarMascotaPorUsuario(@PathVariable("idUsuario") Integer idUsuario) {

		String nombreMetodo = "listarMascotaPorUsuario";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<MascotaDto> listaMascotaDto = mascotaService.listarMascotaPorUsuario(idUsuario);

		if (listaMascotaDto != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<List<MascotaDto>> responseEntity = new ResponseEntity<>(listaMascotaDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@PutMapping("")
	public ResponseEntity<MensajeDto> actualizarMascota(@Valid @RequestBody MascotaEntity mascotaEntity) {
		//TODO log managemente AOP
		String nombreMetodo = "actualizarMascota";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		MensajeDto mensajeDto = mascotaService.actualizarMascota(mascotaEntity);

		if (mensajeDto.getMensaje().contains(Constantes.ACTUALIZACION_MASCOTA_EXITOSO)) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<MensajeDto> responseEntity = new ResponseEntity<>(mensajeDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@PutMapping("/desactivar/{idMascota}")
	public ResponseEntity<MensajeDto> desactivarMascota(@PathVariable("idMascota") Integer idMascota) {

		String nombreMetodo = "desactivarMascota";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		MensajeDto mensajeDto = mascotaService.desactivarMascota(idMascota);

		if (mensajeDto.getMensaje().contains(Constantes.DESACTIVACION_MASCOTA_EXITOSO)) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<MensajeDto> responseEntity = new ResponseEntity<>(mensajeDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

}
