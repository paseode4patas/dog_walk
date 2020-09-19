package com.dogwalk.controller;

import com.dogwalk.dto.HorarioDiarioDto;
import com.dogwalk.dto.HorarioMesDto;
import com.dogwalk.dto.MensajeDto;
import com.dogwalk.service.HorarioService;
import com.dogwalk.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HorarioService horarioService;

	@PostMapping("/{mes}/{idPaseador}")
	public ResponseEntity<Boolean> generarHorario(@PathVariable("mes") Integer mes,
	                                              @PathVariable("idPaseador") Integer idPaseador) {

		String nombreMetodo = "generarHorario";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		boolean horarioGenerado = horarioService.generarHorario(mes, idPaseador);

		if (horarioGenerado) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(horarioGenerado, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/paseador/{idPaseador}")
	public ResponseEntity<List<HorarioMesDto>> listarHorarioMensualPorPaseador(
			@PathVariable("idPaseador") Integer idPaseador) {

		String nombreMetodo = "listarHorarioMensualPorPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<HorarioMesDto> listaHorarioMensualDto = horarioService.listarHorarioMensualPorPaseador(idPaseador);

		if (listaHorarioMensualDto != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<List<HorarioMesDto>> responseEntity = new ResponseEntity<>(listaHorarioMensualDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/paseador/{idPaseador}/{fechaPaseo}")
	public ResponseEntity<List<HorarioDiarioDto>> listarHorarioDiarioPorPaseadorYFecha(
			@PathVariable("idPaseador") Integer idPaseador, @PathVariable("fechaPaseo") String fechaPaseo) {

		String nombreMetodo = "listarHorarioDiarioPorPaseadorYFecha";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<HorarioDiarioDto> listaHorarioDto = horarioService.listarHorarioDiarioPorPaseadorYFecha(idPaseador,
				fechaPaseo);

		if (listaHorarioDto != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<List<HorarioDiarioDto>> responseEntity = new ResponseEntity<>(listaHorarioDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/fecha/{mes}/{anio}")
	public ResponseEntity<List<HorarioMesDto>> listarHorarioMensualPorFecha(@PathVariable("mes") String mes,
	                                                                        @PathVariable("anio") String anio) {

		String nombreMetodo = "listarHorarioMensualPorFecha";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<HorarioMesDto> listaHorarioMensualDto = horarioService.listarHorarioMensualPorFecha(mes, anio);

		if (listaHorarioMensualDto != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<List<HorarioMesDto>> responseEntity = new ResponseEntity<>(listaHorarioMensualDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@PutMapping("/add/{idPaseador}/{fecha}/{mes}/{anio}")
	public ResponseEntity<HorarioMesDto> agregarDia(@PathVariable("idPaseador") Integer idPaseador, @PathVariable("fecha") String fecha, @PathVariable("mes") String mes, @PathVariable("anio") String anio) {
		HorarioMesDto horarioMesDto = horarioService.addDay(idPaseador, fecha, mes, anio);
		if (horarioMesDto == null)
			return ResponseEntity.badRequest().build();


		return new ResponseEntity<>(horarioMesDto, HttpStatus.OK);
	}

	@PutMapping("/remove/{idPaseador}/{fecha}/{mes}/{anio}")
	public ResponseEntity<MensajeDto> borrarDia(@PathVariable("idPaseador") Integer idPaseador, @PathVariable("fecha") String fecha, @PathVariable("mes") String mes, @PathVariable("anio") String anio) {
		boolean result = horarioService.borrarDia(idPaseador, fecha, mes, anio);
		if (!result)
			return ResponseEntity.badRequest().build();


		return null;
	}
}
