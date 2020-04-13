package com.dogwalk.service;

import java.util.Objects;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalk.dto.MensajeDto;
import com.dogwalk.dto.UsuarioDto;
import com.dogwalk.entity.UsuarioEntity;
import com.dogwalk.repository.UsuarioRepository;
import com.dogwalk.util.Constantes;
import com.dogwalk.util.Util;

@Service
public class UsuarioService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  CorreoService correoService;

  public UsuarioDto crearUsuario(UsuarioEntity usuarioEntity) {

    String nombreMetodo = "crearUsuario";
    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_INICIO);

    UsuarioDto usuarioDto = new UsuarioDto();
    String contrasenaEncriptada = "";
    String mensaje = Constantes.CREACION_USUARIO_FALLIDO;

    contrasenaEncriptada = Util.encriptarConBase64(usuarioEntity.getContrasena().trim());
    usuarioEntity.setContrasena(contrasenaEncriptada);
    usuarioEntity.setCambiarContrasena(Constantes.DESACTIVADO);

    UsuarioEntity nuevoUsuario = usuarioRepository.save(usuarioEntity);

    if (nuevoUsuario != null && nuevoUsuario.getId() > 0) {

      usuarioDto.setId(nuevoUsuario.getId());
      usuarioDto.setNombreUsuario(nuevoUsuario.getNombreUsuario());
      usuarioDto.setTipoUsuario(nuevoUsuario.getTipoUsuario());
      usuarioDto.setCambiarContrasena(nuevoUsuario.isCambiarContrasena());
      mensaje = Constantes.CREACION_USUARIO_EXITOSO;

    }

    usuarioDto.setMensaje(mensaje);

    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_FIN);

    return usuarioDto;
  }

  public UsuarioDto login(String usuario, String contrasena) {

    String nombreMetodo = "login";
    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_INICIO);

    UsuarioDto loginDto = new UsuarioDto();
    String contrasenaEncriptada = "";
    String mensaje = Constantes.LOGIN_FALLIDO;
    boolean datosValidos = Stream.of(usuario, contrasena).allMatch(Objects::nonNull);

    if (datosValidos) {

      usuario = usuario.toUpperCase().trim();
      contrasena = contrasena.trim();
      contrasenaEncriptada = Util.encriptarConBase64(contrasena);

      UsuarioEntity usuarioEntity =
          usuarioRepository.findByNombreUsuarioAndContrasena(usuario, contrasenaEncriptada);

      if (usuarioEntity != null && usuarioEntity.getId() > 0) {

        loginDto.setId(usuarioEntity.getId());
        loginDto.setNombreUsuario(usuarioEntity.getNombreUsuario());
        loginDto.setTipoUsuario(usuarioEntity.getTipoUsuario());
        loginDto.setCambiarContrasena(usuarioEntity.isCambiarContrasena());
        mensaje = Constantes.LOGIN_EXITOSO;

      }

    }

    loginDto.setMensaje(mensaje);

    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_FIN);

    return loginDto;
  }

  public MensajeDto cambiarContrasena(Integer idUsuario, String contrasenaActual,
      String contrasenaNueva) {

    String nombreMetodo = "cambiarContrasena";
    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_INICIO);

    String contrasenaActualEncriptada = "";
    String contrasenaEncriptada = "";
    MensajeDto mensajeDto = new MensajeDto();
    String mensaje = "";
    boolean datosValidos =
        Stream.of(idUsuario, contrasenaActual, contrasenaNueva).allMatch(Objects::nonNull);

    if (datosValidos) {

      UsuarioEntity usuarioEntity = usuarioRepository.findUsuarioById(idUsuario);

      if (usuarioEntity != null && usuarioEntity.getId() > 0) {

        contrasenaActualEncriptada = Util.encriptarConBase64(contrasenaActual.trim());

        if (usuarioEntity.getContrasena().equals(contrasenaActualEncriptada)) {

          contrasenaEncriptada = Util.encriptarConBase64(contrasenaNueva.trim());
          usuarioEntity.setContrasena(contrasenaEncriptada);
          usuarioEntity.setCambiarContrasena(Constantes.DESACTIVADO);
          usuarioRepository.save(usuarioEntity);

          mensaje = Constantes.CAMBIO_CONTRASENA_EXITOSO;

        } else {
          mensaje = Constantes.CAMBIO_CONTRASENA_FALLIDO;
        }

      }

    }

    mensajeDto.setMensaje(mensaje);

    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_FIN);

    return mensajeDto;
  }

  public MensajeDto recuperarContrasena(String correo) {

    String nombreMetodo = "recuperarContrasena";
    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_INICIO);

    String contrasenaAutogenerada = "";
    String contrasenaEncriptada = "";
    String mensaje = Constantes.ENVIO_CONTRASENA_FALLIDO;

    boolean envioCorrecto = false;

    MensajeDto mensajeDto = new MensajeDto();

    UsuarioEntity usuarioEntity = usuarioRepository.findByNombreUsuario(correo);

    if (usuarioEntity != null && usuarioEntity.getId() > 0) {

      contrasenaAutogenerada = Util.generarPasswordTemporal();
      contrasenaEncriptada = Util.encriptarConBase64(contrasenaAutogenerada);

      usuarioEntity.setContrasena(contrasenaEncriptada);
      usuarioEntity.setCambiarContrasena(Constantes.ACTIVADO);

      envioCorrecto = correoService.enviarCorreo(correo, contrasenaAutogenerada);

      if (envioCorrecto) {

        usuarioRepository.save(usuarioEntity);
        mensaje = Constantes.ENVIO_CONTRASENA_EXITOSO;

      }

    }

    mensajeDto.setMensaje(mensaje);

    logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_USUARIO, nombreMetodo,
        Constantes.LOG_METODO_FIN);

    return mensajeDto;
  }

}
