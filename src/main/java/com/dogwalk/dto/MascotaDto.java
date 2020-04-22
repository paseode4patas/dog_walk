package com.dogwalk.dto;

public class MascotaDto {

	private Integer id;
	private String nombre;
	private String caracteristica1;
	private String caracteristica2;
	private String mensaje;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaracteristica1() {
		return caracteristica1;
	}

	public void setCaracteristica1(String caracteristica1) {
		this.caracteristica1 = caracteristica1;
	}

	public String getCaracteristica2() {
		return caracteristica2;
	}

	public void setCaracteristica2(String caracteristica2) {
		this.caracteristica2 = caracteristica2;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
