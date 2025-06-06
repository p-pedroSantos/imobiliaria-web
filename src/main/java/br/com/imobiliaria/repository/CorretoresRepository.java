package br.com.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.model.Corretores;

public interface CorretoresRepository extends JpaRepository<Corretores, Integer>{

	boolean existsByEmail(String email);

}
