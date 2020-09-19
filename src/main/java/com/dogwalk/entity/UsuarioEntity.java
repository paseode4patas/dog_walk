package com.dogwalk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
@Data
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

	@Column(name = "cambiar_contrasena")
	private Boolean cambiarContrasena;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_info_id")
	private UsuarioInfoEntity usuarioInfo;

/*
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

	public Boolean getCambiarContrasena() {
		return cambiarContrasena;
	}

	public void setCambiarContrasena(Boolean cambiarContrasena) {
		this.cambiarContrasena = cambiarContrasena;
	}
*/

}
