package com.dogwalk.controller;

import com.dogwalk.dto.MensajeDto;
import com.dogwalk.dto.UsuarioInfoDto;
import com.dogwalk.entity.UsuarioInfoEntity;
import com.dogwalk.service.PaseadorService;
import com.dogwalk.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paseador")
public class UsuarioInfoController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PaseadorService paseadorService;

	/*@Autowired //Mapper added to solve crear paseador
	ModelMapper modelMapper;
*/
	@PostMapping("")
	public ResponseEntity<UsuarioInfoDto> crearPaseador(@Valid @RequestBody JsonNode objectNode) {
		/* WORK AROUND @RequestBody UsuarioInfoEntity usuarioInfoEntity*/
/*
		UsuarioInfoEntity usuarioInfoEntity = modelMapper.map(objectNode, UsuarioInfoEntity.class);*/
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UsuarioInfoEntity usuarioInfoEntity = null;
		try {
			usuarioInfoEntity = objectMapper.treeToValue(objectNode, UsuarioInfoEntity.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String password = objectNode.get("password").asText();
		int usuarioId = objectNode.get("usuario_id") != null ? objectNode.get("usuario_id").asInt() : 0;

		if (password.isEmpty() || usuarioId == 0)
			return ResponseEntity.badRequest().build();

		String nombreMetodo = "crearPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);
		//TODO validation
		HttpStatus status = HttpStatus.BAD_REQUEST;
		UsuarioInfoDto usuarioInfoDto = paseadorService.crearPaseador(usuarioId, usuarioInfoEntity, password);

		if (usuarioInfoDto.getId() != null && usuarioInfoDto.getId() > 0) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<UsuarioInfoDto> responseEntity = new ResponseEntity<>(usuarioInfoDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("")
	public ResponseEntity<List<UsuarioInfoDto>> listarPaseador() {

		String nombreMetodo = "listarPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<UsuarioInfoDto> listaUsuarioInfoDto = paseadorService.listarPaseador();

		if (listaUsuarioInfoDto != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<List<UsuarioInfoDto>> responseEntity = new ResponseEntity<>(listaUsuarioInfoDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@PutMapping("")
	public ResponseEntity<MensajeDto> actualizarPaseador(@RequestBody UsuarioInfoEntity usuarioInfoEntity){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		MensajeDto mensajeDto = paseadorService.actualizarPaseador(usuarioInfoEntity);

		if (mensajeDto.getMensaje().contains(Constantes.ACTUALIZACION_PASEADOR_EXITOSO)){
			status = HttpStatus.OK;
			//TODO LOG
		} else {
			//LOG
		}

		ResponseEntity<MensajeDto> responseEntity = new ResponseEntity<>(mensajeDto, status);

		//TODO LOG

		return responseEntity;
	}

	@PutMapping("/desactivar/{idPaseador}")
	public  ResponseEntity<MensajeDto> desactivarPaseador(@PathVariable("idPaseador") Integer idPaseador){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		MensajeDto mensajeDto = paseadorService.desactivarPaseador(idPaseador);

		if (mensajeDto.getMensaje().contains(Constantes.DESACTIVACION_PASEADOR_EXITOSO)){
			status = HttpStatus.OK;
			//TODO LOG
		} else {
			//LOG
		}

		ResponseEntity<MensajeDto> responseEntity = new ResponseEntity<>(mensajeDto, status);

		//LOG

		return responseEntity;
	}

}
