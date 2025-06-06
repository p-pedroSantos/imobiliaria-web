package br.com.imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Anuncios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "imovel_id", nullable = false)
	private Imoveis imovel;

	@ManyToOne
	@JoinColumn(name = "corretor_id", nullable = false)
	private Corretores corretor;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Imoveis getImovel() {
		return imovel;
	}
	public void setImovel(Imoveis imovel) {
		this.imovel = imovel;
	}
	public Corretores getCorretor() {
		return corretor;
	}
	public void setCorretor(Corretores corretor) {
		this.corretor = corretor;
	}
	
	

	
	
}
