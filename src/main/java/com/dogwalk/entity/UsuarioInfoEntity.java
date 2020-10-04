package com.dogwalk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario_info")
@Data
public class UsuarioInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 8, max = 8)
	@Column(name = "dni")
	private String dni;

	@NotNull
	@Email
	@Column(name = "email")
	private String email;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "nombres")
	private String nombres;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "apellidos")
	private String apellidos;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "direccion")
	private String direccion;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "distrito")
	private String distrito;

	@NotNull
	@Size(min = 9, max = 9)
	@Column(name = "celular")
	private String celular;

	@Column(name = "estado")
	private Boolean estado;

	@OneToOne(mappedBy = "usuarioInfo")
	private UsuarioEntity usuario;

/*
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
*/

}
