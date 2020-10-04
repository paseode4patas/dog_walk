package com.dogwalk.dto;

import lombok.Data;

@Data
public class UsuarioDto {

	private Integer id;
	private String nombreUsuario;
	private String tipoUsuario;
	private boolean cambiarContrasena;
	private String mensaje;

	/*public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean isCambiarContrasena() {
		return cambiarContrasena;
	}

	public void setCambiarContrasena(boolean cambiarContrasena) {
		this.cambiarContrasena = cambiarContrasena;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}*/

}
