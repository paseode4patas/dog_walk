package com.dogwalk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "HORARIO_DIARIO")
@Data
public class HorarioDiarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 10, max = 10)
	@Column(name = "fecha")
	private String fecha;

	@NotNull
	@Size(min = 10, max = 100)
	@Column(name = "horario")
	private String horario;

	@NotNull
	@Column(name = "paseador_id")
	private Integer paseadorId;

	@Column(name = "estado")
	private Boolean estado;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private HorarioState state;


	/*public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
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
*/
}
