package com.dogwalk.util;

public class Constantes {

	// Log
	public static final String LOG_FORMATO = "{} - {} - {}";

	// Controller
	public static final String LOG_CONTROLLER_USUARIO = "UsuarioController";
	public static final String LOG_CONTROLLER_UTIL = "UtilController";

	// Service
	public static final String LOG_SERVICE_USUARIO = "UsuarioService";
	public static final String LOG_SERVICE_UTIL = "UtilService";
	public static final String LOG_SERVICE_CORREO = "CorreoService";

	// Método
	public static final String LOG_METODO_INICIO = "Inicio";
	public static final String LOG_METODO_ESTATUS_EXITOSO = "Estatus Correcto";
	public static final String LOG_METODO_ESTATUS_FALLIDO = "Estatus Fallido";
	public static final String LOG_METODO_FIN = "Fin";

	// Login
	public static final String LOGIN_EXITOSO = "Usuario y contraseña correctos";
	public static final String LOGIN_FALLIDO = "Usuario y/o contraseña incorrectos";
	public static final String CAMBIO_CONTRASENA_EXITOSO = "La contraseña se cambió con éxito";
	public static final String CAMBIO_CONTRASENA_FALLIDO = "Error al cambiar la contraseña";
	public static final String ENVIO_CONTRASENA_EXITOSO = "La contraseña se envió con éxito";
	public static final String ENVIO_CONTRASENA_FALLIDO = "Error al enviar la contraseña";
	public static final int LONGITUD_CONTRASENA = 6;

	// Usuario
	public static final String CREACION_USUARIO_EXITOSO = "Usuario creado correctamente";
	public static final String CREACION_USUARIO_FALLIDO = "Error al crear el usuario";

	// Email
	public static final String ASUNTO_CORREO_RECUPERACION = "DogWalk - Recupera tu Contraseña";
	public static final String ENVIO_CORREO_INICIO = "Envío de Correo Iniciado";
	public static final String ENVIO_CORREO_FIN = "Envío de Correo Finalizado";

	public static final boolean ACTIVADO = true;
	public static final boolean DESACTIVADO = false;

}
