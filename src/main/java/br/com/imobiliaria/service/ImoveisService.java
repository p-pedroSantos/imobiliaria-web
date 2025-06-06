package br.com.imobiliaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.model.Imoveis;
import br.com.imobiliaria.repository.AnunciosRepository;
import br.com.imobiliaria.repository.ImoveisRepository;

@Service
public class ImoveisService {

    @Autowired
    private ImoveisRepository imoveisRepository;
    
    @Autowired
    private AnunciosRepository anuncioRepository;

    public Imoveis salvar(Imoveis imovel) {
        if (imovel.getBairro() == null || imovel.getBairro().isBlank()) {
            throw new IllegalArgumentException("O bairro é obrigatório.");
        }
        if (imovel.getDescricao() == null || imovel.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }
        return imoveisRepository.save(imovel);
    }

    public List<Imoveis> listar() {
        return imoveisRepository.findAll();
    }

    public void deletar(Integer id) {
        Imoveis imovel = imoveisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Imóvel não encontrado"));

        boolean temAnuncios = anuncioRepository.existsByImovel(imovel);
        if (temAnuncios) {
            throw new IllegalArgumentException("Não é possível excluir. Este imóvel possui anúncios ativos.");
        }

        imoveisRepository.deleteById(id);
    }

    public Optional<Imoveis> buscarPorId(Integer id) {
        return imoveisRepository.findById(id);
    }
    
    public List<Imoveis> buscarAnucioVazio () {
    	return imoveisRepository.findByAnuncioIsNull();
    }
}

