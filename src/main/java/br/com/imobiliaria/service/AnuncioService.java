package br.com.imobiliaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.model.Anuncios;
import br.com.imobiliaria.model.TipoImoveis;
import br.com.imobiliaria.repository.AnunciosRepository;
import br.com.imobiliaria.repository.CorretoresRepository;
import br.com.imobiliaria.repository.ImoveisRepository;

@Service
public class AnuncioService {

    @Autowired
    private AnunciosRepository anuncioRepository;

    @Autowired
    private ImoveisRepository imoveisRepository;

    @Autowired
    private CorretoresRepository corretoresRepository;

    public Anuncios salvar(Anuncios anuncio) {
        if (anuncio.getImovel() == null) {
            throw new IllegalArgumentException("O imóvel é obrigatório.");
        }

        if (anuncio.getCorretor() == null) {
            throw new IllegalArgumentException("O corretor é obrigatório.");
        }

        boolean imovelJaTemAnuncio = anuncioRepository.existsByImovel(anuncio.getImovel());
        if (imovelJaTemAnuncio) {
            throw new IllegalArgumentException("Este imóvel já possui um anúncio.");
        }

        return anuncioRepository.save(anuncio);
    }

    public List<Anuncios> listar() {
        return anuncioRepository.findAll();
    }

    public void deletar(Integer id) {
        anuncioRepository.deleteById(id);
    }

    public Optional<Anuncios> buscarPorId(Integer id) {
        return anuncioRepository.findById(id);
    }
    
    public List<Anuncios> buscarAnuncios(Integer corretorId, String bairro, TipoImoveis tipo) {
        List<Anuncios> anuncios = anuncioRepository.findAll();

        if (corretorId != null) {
            anuncios = anuncios.stream()
                    .filter(a -> a.getCorretor().getId().equals(corretorId))
                    .toList();
        }

        if (bairro != null && !bairro.isEmpty()) {
            anuncios = anuncios.stream()
                    .filter(a -> a.getImovel().getBairro().equalsIgnoreCase(bairro))
                    .toList();
        }

        if (tipo != null) {
            anuncios = anuncios.stream()
                    .filter(a -> a.getImovel().getTipo() == tipo)
                    .toList();
        }

        return anuncios;
    }


}

