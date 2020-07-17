package com.dogwalk.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalk.dto.HorarioDiarioDto;
import com.dogwalk.dto.HorarioMesDto;
import com.dogwalk.entity.HorarioDiarioEntity;
import com.dogwalk.entity.HorarioMesEntity;
import com.dogwalk.repository.HorarioMesRepository;
import com.dogwalk.repository.HorarioDiarioRepository;
import com.dogwalk.util.Constantes;

@Service
public class HorarioService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HorarioMesRepository horarioMesRepository;

	@Autowired
	HorarioDiarioRepository horarioDiarioRepository;

	@Autowired
	ModelMapper modelMapper;

	public boolean generarHorario(Integer mes, Integer paseadorId) {

		String nombreMetodo = "generarHorario";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_INICIO);

		boolean horarioGenerado = false;
		List<HorarioMesEntity> listaHorarioMesEntity = new ArrayList<>();
		List<HorarioDiarioEntity> listaHorarioDiarioEntity = new ArrayList<>();
		String mensaje = Constantes.GENERACION_HORARIO_FALLIDO;

		if (mes >= 1 && mes <= 12) {

			// Fechas
			int[] diasDelMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			int totalDias = diasDelMes[mes - 1];

			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo,
					"Total de días a generar: " + totalDias);

			Calendar calendario = Calendar.getInstance();
			int anio = calendario.get(Calendar.YEAR);
			int dia = calendario.get(Calendar.DAY_OF_MONTH);
			boolean esAnioBisiesto = new GregorianCalendar().isLeapYear(anio);

			if (esAnioBisiesto && mes == 2) {
				totalDias++;
			}

			// Horarios
			String[] horarios = { "06:00-07:00", "07:00-08:00", "08:00-09:00", "09:00-10:00", "10:00-11:00",
					"11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00",
					"17:00-18:00", "18:00-19:00", "19:00-20:00", "20:00-21:00", "21:00-22:00", "22:00-23:00" };

			HorarioMesEntity horarioMesEntity;
			HorarioDiarioEntity horarioDiarioEntity;

			try {

				// Horario Mensual
				for (int i = dia; i <= totalDias; i++) {

					calendario.clear();
					calendario.set(anio, mes - 1, i);

					Date fecha = calendario.getTime();
					SimpleDateFormat formato = new SimpleDateFormat(Constantes.FORMATO_FECHA);
					String fechaFormateada = formato.format(fecha);

					horarioMesEntity = new HorarioMesEntity();
					horarioMesEntity.setFecha(fechaFormateada);
					horarioMesEntity.setMes(String.valueOf(mes));
					horarioMesEntity.setAnio(String.valueOf(anio));
					horarioMesEntity.setPaseadorId(paseadorId);
					horarioMesEntity.setEstado(Constantes.ESTADO_ACTIVO);

					listaHorarioMesEntity.add(horarioMesEntity);

					// Horario Diario
					for (int j = 0; j < horarios.length; j++) {

						horarioDiarioEntity = new HorarioDiarioEntity();
						horarioDiarioEntity.setFecha(fechaFormateada);
						horarioDiarioEntity.setHorario(horarios[j]);
						horarioDiarioEntity.setPaseadorId(paseadorId);
						horarioDiarioEntity.setEstado(Constantes.ESTADO_ACTIVO);

						listaHorarioDiarioEntity.add(horarioDiarioEntity);
					}
				}

				horarioMesRepository.saveAll(listaHorarioMesEntity);
				horarioDiarioRepository.saveAll(listaHorarioDiarioEntity);
				horarioGenerado = true;
				mensaje = Constantes.GENERACION_HORARIO_EXITOSO;

			} catch (Exception e) {
				logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, e.getMessage());
			} finally {
				logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, mensaje);
			}

			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo,
					Constantes.LOG_METODO_FIN);
		}

		return horarioGenerado;
	}

	public List<HorarioMesDto> listarHorarioMensualPorPaseador(Integer idPaseador) {

		String nombreMetodo = "listarHorarioMensualPorPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_INICIO);

		List<HorarioMesDto> listaHorarioMensualDto = null;

		try {

			List<HorarioMesEntity> listaHorarioMesEntity = horarioMesRepository
					.findHorarioMensualByPaseadorIdAndEstado(idPaseador, Constantes.ESTADO_ACTIVO);
			listaHorarioMensualDto = modelMapper.map(listaHorarioMesEntity, new TypeToken<List<HorarioMesDto>>() {
			}.getType());

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return listaHorarioMensualDto;
	}

	public List<HorarioDiarioDto> listarHorarioDiarioPorPaseadorYFecha(Integer idPaseador, String fechaPaseo) {

		String nombreMetodo = "listarHorarioDiarioPorPaseadorYFecha";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_INICIO);

		List<HorarioDiarioDto> listaHorarioDiarioDto = null;

		try {

			// Valida formato de fecha
			if (fechaPaseo != null && fechaPaseo.length() == 8) {

				String fechaBuscar = fechaPaseo.substring(0, 2) + "/" + fechaPaseo.substring(2, 4) + "/"
						+ fechaPaseo.substring(4, fechaPaseo.length());

				logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo,
						"Fecha Válida: " + fechaBuscar);

				List<HorarioDiarioEntity> listaHorarioEntity = horarioDiarioRepository
						.findHorarioByPaseadorIdAndFechaAndEstado(idPaseador, fechaBuscar, Constantes.ESTADO_ACTIVO);

				listaHorarioDiarioDto = modelMapper.map(listaHorarioEntity, new TypeToken<List<HorarioDiarioDto>>() {
				}.getType());
			}

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return listaHorarioDiarioDto;
	}

	public List<HorarioMesDto> listarHorarioMensualPorFecha(String mes, String anio) {

		String nombreMetodo = "listarHorarioMensualPorFecha";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_INICIO);

		List<HorarioMesDto> listaHorarioMensualDto = null;

		try {

			List<HorarioMesEntity> listaHorarioMesEntity = horarioMesRepository
					.findHorarioMensualByMesAndAnioAndEstadoOrderByFechaAscPaseadorIdAsc(mes, anio,
							Constantes.ESTADO_ACTIVO);
			listaHorarioMensualDto = modelMapper.map(listaHorarioMesEntity, new TypeToken<List<HorarioMesDto>>() {
			}.getType());

		} catch (Exception e) {
			logger.error(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_HORARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return listaHorarioMensualDto;
	}

}
