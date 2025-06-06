package br.com.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.model.Anuncios;
import br.com.imobiliaria.model.Corretores;
import br.com.imobiliaria.model.Imoveis;

public interface AnunciosRepository extends JpaRepository<Anuncios, Integer>{

	boolean existsByImovel(Imoveis imovel);

	boolean existsByCorretor(Corretores corretor);

}
