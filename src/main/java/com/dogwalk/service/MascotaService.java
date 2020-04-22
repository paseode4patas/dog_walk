package com.dogwalk.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalk.dto.MascotaDto;
import com.dogwalk.dto.MensajeDto;
import com.dogwalk.entity.MascotaEntity;
import com.dogwalk.repository.MascotaRepository;
import com.dogwalk.util.Constantes;

@Service
public class MascotaService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MascotaRepository mascotaRepository;

	@Autowired
	UtilService utilService;

	@Autowired
	ModelMapper modelMapper;

	public MascotaDto crearMascota(MascotaEntity mascotaEntity) {

		String nombreMetodo = "crearMascota";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_INICIO);

		MascotaDto mascotaDto = new MascotaDto();
		String mensaje = Constantes.CREACION_MASCOTA_FALLIDO;

		try {

			mascotaEntity.setEstado(Constantes.ESTADO_ACTIVO);
			MascotaEntity nuevaMascota = mascotaRepository.save(mascotaEntity);
			mascotaDto = modelMapper.map(nuevaMascota, MascotaDto.class);
			mensaje = Constantes.CREACION_MASCOTA_EXITOSO;

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, e.getMessage());
		} finally {
			mascotaDto.setMensaje(mensaje);
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, mensaje);
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return mascotaDto;
	}

	public List<MascotaDto> listarMascotaPorUsuario(Integer idUsuario) {

		String nombreMetodo = "listarMascotaPorUsuario";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_INICIO);

		List<MascotaDto> listaMascotaDto = null;

		try {

			List<MascotaEntity> listaMascotaEntity = mascotaRepository.findMascotaByUsuarioIdAndEstado(idUsuario,
					Constantes.ESTADO_ACTIVO);
			listaMascotaDto = modelMapper.map(listaMascotaEntity, new TypeToken<List<MascotaDto>>() {
			}.getType());

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return listaMascotaDto;
	}

	public MensajeDto actualizarMascota(MascotaEntity mascotaEntity) {

		String nombreMetodo = "actualizarMascota";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_INICIO);

		MensajeDto mensajeDto = new MensajeDto();
		String mensaje = Constantes.ACTUALIZACION_MASCOTA_FALLIDO;

		try {

			if (mascotaRepository.existsById(mascotaEntity.getId())) {
				mascotaEntity.setEstado(Constantes.ESTADO_ACTIVO);
				mascotaRepository.save(mascotaEntity);
				mensaje = Constantes.ACTUALIZACION_MASCOTA_EXITOSO;
			}

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, e.getMessage());
		} finally {
			mensajeDto.setMensaje(mensaje);
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, mensaje);
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return mensajeDto;
	}

	public MensajeDto desactivarMascota(Integer idMascota) {

		String nombreMetodo = "desactivarMascota";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_INICIO);

		MensajeDto mensajeDto = new MensajeDto();
		String mensaje = Constantes.DESACTIVACION_MASCOTA_FALLIDO;

		try {

			MascotaEntity mascota = mascotaRepository.findMascotaById(idMascota);
			mascota.setEstado(Constantes.ESTADO_INACTIVO);
			mascotaRepository.save(mascota);
			mensaje = Constantes.DESACTIVACION_MASCOTA_EXITOSO;

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, e.getMessage());
		} finally {
			mensajeDto.setMensaje(mensaje);
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, mensaje);
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return mensajeDto;
	}

}
