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
@Table(name = "PASEADOR")
public class PaseadorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 8, max = 8)
	@Column(name = "dni")
	private String dni;

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

}
