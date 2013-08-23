package br.com.maps.labrador.services.model;

/**
 * Representa uma corrida, utilizada por CorreService.java
 * @see CorreService
 * 
 * @author renan.oliveira
 */
public class Corrida {

	private String percurso;
	private Double distancia;
	private Double tempo;

	public Corrida(String percurso, Double distancia, Double tempo) {
		this.percurso = percurso;
		this.distancia = distancia;
		this.tempo = tempo;
	}

	public String getPercurso() {
		return percurso;
	}

	public Double getDistancia() {
		return distancia;
	}

	public Double getTempo() {
		return tempo;
	}
	
	public Double getMedia(){
		return tempo/distancia;
	}

}
