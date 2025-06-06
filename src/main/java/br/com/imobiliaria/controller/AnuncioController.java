package br.com.imobiliaria.controller;

import br.com.imobiliaria.model.Anuncios;
import br.com.imobiliaria.model.Imoveis;
import br.com.imobiliaria.model.TipoImoveis;
import br.com.imobiliaria.service.AnuncioService;
import br.com.imobiliaria.service.CorretoresService;
import br.com.imobiliaria.service.ImoveisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private ImoveisService imoveisService;

    @Autowired
    private CorretoresService corretoresService;

    // ✅ Página principal: Lista com filtros
    @GetMapping
    public String listarComFiltros(
            @RequestParam(required = false) Integer corretorId,
            @RequestParam(required = false) String bairro,
            @RequestParam(required = false) TipoImoveis tipo,
            Model model) {

        List<Anuncios> anuncios = anuncioService.buscarAnuncios(corretorId, bairro, tipo);

        model.addAttribute("anuncios", anuncios);
        model.addAttribute("corretores", corretoresService.listar());
        model.addAttribute("bairros", imoveisService.listar()
                                        .stream()
                                        .map(Imoveis::getBairro)
                                        .distinct()
                                        .toList());
        model.addAttribute("tipos", TipoImoveis.values());

        return "anuncios/listarAnuncios";
    }

    // ✅ Página de formulário para novo anúncio
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("anuncio", new Anuncios());
        model.addAttribute("corretores", corretoresService.listar());
        model.addAttribute("imoveisDisponiveis", imoveisService.buscarAnucioVazio());
        return "anuncios/formAnuncios";
    }

    // ✅ Salvar novo anúncio ou atualizar existente
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Anuncios anuncio) {
        anuncioService.salvar(anuncio);
        return "redirect:/anuncios";
    }

    // ✅ Editar um anúncio existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Anuncios anuncio = anuncioService.buscarPorId(id).orElseThrow();

        List<Imoveis> imoveisDisponiveis = imoveisService.buscarAnucioVazio();
        imoveisDisponiveis.add(anuncio.getImovel()); // Garante que o imóvel atual do anúncio aparece na edição

        model.addAttribute("anuncio", anuncio);
        model.addAttribute("corretores", corretoresService.listar());
        model.addAttribute("imoveisDisponiveis", imoveisDisponiveis);

        return "anuncios/formAnuncios";
    }

    // ✅ Deletar anúncio
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id, Model model) {
        try {
            anuncioService.deletar(id);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao excluir o anúncio. Verifique vínculos.");
            return listarComFiltros(null, null, null, model);
        }
        return "redirect:/anuncios";
    }
}
