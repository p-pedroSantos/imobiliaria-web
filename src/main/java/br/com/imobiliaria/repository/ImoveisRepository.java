package br.com.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import br.com.imobiliaria.model.Imoveis;

public interface ImoveisRepository extends JpaRepository<Imoveis, Integer>{

	List<Imoveis> findByAnuncioIsNull();

}
