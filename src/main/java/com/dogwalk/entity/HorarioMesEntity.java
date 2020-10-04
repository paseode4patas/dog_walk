package com.dogwalk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "HORARIO_MES")
@Data
public class HorarioMesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 1, max = 2)
	@Column(name = "mes")
	private String mes;

	@NotNull
	@Size(min = 4, max = 4)
	@Column(name = "anio")
	private String anio;

	@NotNull
	@Size(min = 10, max = 10)
	@Column(name = "fecha")
	private String fecha;

	@NotNull
	@Column(name = "paseador_id")
	private Integer paseadorId;

	@Column(name = "estado")
	private Boolean estado;

	/*public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

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
	}*/

}
