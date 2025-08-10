package com.pruebas.banco.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bancos")
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@Column(name = "total_transferencia")
	private int totaltransferencia;

	public Banco() {

	}

	public Banco(Long id, String nombre, int totaltransferencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.totaltransferencia = totaltransferencia;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTotaltransferencia() {
		return totaltransferencia;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTotaltransferencia(int totaltransferencia) {
		this.totaltransferencia = totaltransferencia;
	}

}
