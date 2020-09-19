package com.dogwalk.service;

import com.dogwalk.dto.MensajeDto;
import com.dogwalk.dto.UsuarioInfoDto;
import com.dogwalk.entity.UsuarioEntity;
import com.dogwalk.entity.UsuarioInfoEntity;
import com.dogwalk.repository.UsuarioInfoRepository;
import com.dogwalk.repository.UsuarioRepository;
import com.dogwalk.util.Constantes;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PaseadorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UsuarioInfoRepository usuarioInfoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	HorarioService horarioService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UtilService utilService;

	public UsuarioInfoDto crearPaseador(int usuarioId, UsuarioInfoEntity usuarioInfoEntity, String password) {
//Transaction here
		String nombreMetodo = "crearPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		UsuarioEntity usuario = usuarioRepository.findUsuarioById(usuarioId);

		if (usuario == null)
			return new UsuarioInfoDto(); //Will catch as error in controller

		UsuarioInfoDto usuarioInfoDto = new UsuarioInfoDto();
		String mensaje = Constantes.CREACION_PASEADOR_FALLIDO;

		try {

			usuarioInfoEntity.setEstado(Constantes.ESTADO_ACTIVO);
			usuarioInfoEntity.setUsuario(usuario);
			//UsuarioInfoEntity nuevoInfo = usuarioInfoRepository.save(usuarioInfoEntity);
//			usuarioInfoDto = modelMapper.map(nuevoInfo, UsuarioInfoDto.class);

			usuario.setContrasena(utilService.encriptarConBase64(password));
			usuario.setUsuarioInfo(usuarioInfoEntity);

			UsuarioEntity nuevoUsuario = usuarioRepository.save(usuario);

			usuarioInfoDto = modelMapper.map(nuevoUsuario.getUsuarioInfo(), UsuarioInfoDto.class);

			usuarioInfoDto.setPasswordCreado(true);

			// Genera horario del mes por defecto
			Calendar calendario = Calendar.getInstance();

			int mesActual = calendario.get(Calendar.MONTH) + 1;
			boolean horarioGenerado = horarioService.generarHorario(mesActual, usuarioInfoDto.getId());

			if (horarioGenerado) {
				mensaje = Constantes.CREACION_PASEADOR_EXITOSO;
			} else {
				usuarioInfoDto.setId(null);
			}

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, e.getMessage());
		} finally {
			usuarioInfoDto.setMensaje(mensaje);
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, mensaje);
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, Constantes.LOG_METODO_FIN);

		return usuarioInfoDto;
	}

	public List<UsuarioInfoDto> listarPaseador() {

		String nombreMetodo = "listarPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		List<UsuarioInfoDto> listaMascotaDto = null;

		try {

			List<UsuarioInfoEntity> listaUsuarioInfoEntity = usuarioInfoRepository
					.findPaseadorByEstado(Constantes.ESTADO_ACTIVO);
			listaMascotaDto = modelMapper.map(listaUsuarioInfoEntity, new TypeToken<List<UsuarioInfoDto>>() {
			}.getType());

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_PASEADOR, nombreMetodo, Constantes.LOG_METODO_FIN);

		return listaMascotaDto;
	}

	public MensajeDto actualizarPaseador(UsuarioInfoEntity usuarioInfoEntity) {
		//TODO log

		MensajeDto mensajeDto = new MensajeDto();
		String mensaje = Constantes.ACTUALIZACION_PASEADOR_FALLIDO;

		try {
			if(usuarioInfoRepository.existsById(usuarioInfoEntity.getId())){
				usuarioInfoEntity.setEstado(Constantes.ESTADO_ACTIVO);
				usuarioInfoRepository.save(usuarioInfoEntity);
				mensaje = Constantes.ACTUALIZACION_PASEADOR_EXITOSO;
			}
		} catch (Exception e){
			//logger.error();//TODO
		} finally {
			mensajeDto.setMensaje(mensaje);
			//TODO
		}
		return mensajeDto;
	}

	public MensajeDto desactivarPaseador(Integer idPaseador) {
		//TODO log
		MensajeDto mensajeDto = new MensajeDto();
		String mensaje = Constantes.DESACTIVACION_PASEADOR_FALLIDO;

		try {
			UsuarioInfoEntity paseador = usuarioInfoRepository.findById(idPaseador).get();
			paseador.setEstado(Constantes.ESTADO_INACTIVO);
			usuarioInfoRepository.save(paseador);
			mensaje = Constantes.DESACTIVACION_PASEADOR_EXITOSO;
		} catch (Exception e) {
			//TODO
		} finally {
			mensajeDto.setMensaje(mensaje);
			//TODO
		}

		//TODO

		return mensajeDto;
	}
}
