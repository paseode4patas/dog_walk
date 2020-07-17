package com.dogwalk.service;

import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalk.dto.PaseadorDto;
import com.dogwalk.entity.PaseadorEntity;
import com.dogwalk.repository.PaseadorRepository;
import com.dogwalk.util.Constantes;

@Service
public class PaseadorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PaseadorRepository paseadorRepository;

	@Autowired
	HorarioService horarioService;

	@Autowired
	ModelMapper modelMapper;

	public PaseadorDto crearPaseador(PaseadorEntity paseadorEntity) {

		String nombreMetodo = "crearPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		PaseadorDto paseadorDto = new PaseadorDto();
		String mensaje = Constantes.CREACION_PASEADOR_FALLIDO;

		try {

			paseadorEntity.setEstado(Constantes.ESTADO_ACTIVO);
			PaseadorEntity nuevoPaseador = paseadorRepository.save(paseadorEntity);
			paseadorDto = modelMapper.map(nuevoPaseador, PaseadorDto.class);

			// Genera horario del mes por defecto
			Calendar calendario = Calendar.getInstance();

			int mesActual = calendario.get(Calendar.MONTH) + 1;
			boolean horarioGenerado = horarioService.generarHorario(mesActual, paseadorDto.getId());

			if (horarioGenerado) {
				mensaje = Constantes.CREACION_PASEADOR_EXITOSO;
			} else {
				paseadorDto.setId(null);
			}

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, e.getMessage());
		} finally {
			paseadorDto.setMensaje(mensaje);
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, mensaje);
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, Constantes.LOG_METODO_FIN);

		return paseadorDto;
	}

	public List<PaseadorDto> listarPaseador() {

		String nombreMetodo = "listarPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		List<PaseadorDto> listaMascotaDto = null;

		try {

			List<PaseadorEntity> listaPaseadorEntity = paseadorRepository
					.findPaseadorByEstado(Constantes.ESTADO_ACTIVO);
			listaMascotaDto = modelMapper.map(listaPaseadorEntity, new TypeToken<List<PaseadorDto>>() {
			}.getType());

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, Constantes.LOG_METODO_FIN);

		return listaMascotaDto;
	}

}
