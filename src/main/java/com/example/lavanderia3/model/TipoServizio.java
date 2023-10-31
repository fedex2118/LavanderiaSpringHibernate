package com.example.lavanderia3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_servizio")
public class TipoServizio {
	
	@Id
	@Column(name = "tipo")
	private String tipo;

	public TipoServizio() {
		
	}
	
	public TipoServizio(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
