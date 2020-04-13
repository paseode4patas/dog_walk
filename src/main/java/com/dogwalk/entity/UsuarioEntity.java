package com.dogwalk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 10, max = 100)
	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@NotNull
	@Size(min = 6, max = 50)
	@Column(name = "contrasena")
	private String contrasena;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "tipo_usuario")
	private String tipoUsuario;

	@NotNull
	@Column(name = "cambiar_contrasena")
	private boolean cambiarContrasena;

	public Integer getId() {
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

}
