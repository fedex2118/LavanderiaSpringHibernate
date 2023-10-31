package com.example.lavanderia3.model;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servizio")
public class Servizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "tipo")
    private TipoServizio tipoServizio;
    
    @Column(name = "costo")
	private Double costo;
	
    @JsonProperty("tempo_esecuzione")
	@Column(name = "tempo_esecuzione")
	private Time tempoEsecuzione;
	
	public Servizio() {
		
	}
	
	public Servizio(Long id, TipoServizio tipo, Double costo, Time tempoEsecuzione) {
		this.id = id;
		this.tipoServizio = tipo;
		this.costo = costo;
		this.tempoEsecuzione = tempoEsecuzione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoServizio getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(TipoServizio tipo) {
		this.tipoServizio = tipo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Time getTempoEsecuzione() {
		return tempoEsecuzione;
	}

	public void setTempoEsecuzione(Time tempoEsecuzione) {
		this.tempoEsecuzione = tempoEsecuzione;
	}
}
