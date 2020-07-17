package com.dogwalk.dto;

public class HorarioMesDto {

	private String fecha;
	private Integer paseadorId;
	private Boolean estado;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getPaseadorId() {
		return paseadorId;
	}

	public void setPaseadorId(Integer paseadorId) {
		this.paseadorId = paseadorId;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
