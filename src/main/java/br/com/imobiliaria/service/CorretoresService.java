package br.com.imobiliaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.model.Corretores;
import br.com.imobiliaria.repository.AnunciosRepository;
import br.com.imobiliaria.repository.CorretoresRepository;
import jakarta.transaction.Transactional;

@Service
public class CorretoresService {

    @Autowired
    private CorretoresRepository corretoresRepository;
    
    @Autowired
    private AnunciosRepository anuncioRepository;

    public Corretores salvar(Corretores corretor) {
        if (corretor.getNome() == null || corretor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (corretor.getEmail() == null || corretor.getEmail().isBlank()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }
        if (corretor.getTelefone() == null || corretor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone é obrigatório.");
        }

        return corretoresRepository.save(corretor);
    }

    public List<Corretores> listar() {
        return corretoresRepository.findAll();
    }

    public void deletar(Integer id) {
        Corretores corretor = corretoresRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Corretor não encontrado"));

        boolean temAnuncios = anuncioRepository.existsByCorretor(corretor);
        if (temAnuncios) {
            throw new IllegalArgumentException("Não é possível excluir. Este corretor possui anúncios ativos.");
        }

        corretoresRepository.deleteById(id);
    }

    public Optional<Corretores> buscarPorId(Integer id) {
        return corretoresRepository.findById(id);
    }
}
