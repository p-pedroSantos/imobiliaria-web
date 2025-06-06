package br.com.imobiliaria.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Imoveis {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Nonnull
	private TipoImoveis tipo;
	private String bairro;
	private String descricao; 
	@OneToOne(mappedBy = "imovel")
	private Anuncios anuncio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoImoveis getTipo() {
		return tipo;
	}
	public void setTipo(TipoImoveis tipo) {
		this.tipo = tipo;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String toString() {
		return "Imoveis [id=" + id + ", tipo=" + tipo + ", bairro=" + bairro + ", descricao=" + descricao + "]";
	}
	
}
