package br.com.imobiliaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import br.com.imobiliaria.model.Imoveis;
import br.com.imobiliaria.model.TipoImoveis;
import br.com.imobiliaria.repository.ImoveisRepository;
import br.com.imobiliaria.service.ImoveisService;

@Controller
@RequestMapping("/imoveis")
public class ImoveisController {

	//@Autowired
    //private ImoveisRepository imoveisRepository;
	@Autowired
    private ImoveisService imoveisService;

    // Envia os valores do enum para o select do tipo
    @ModelAttribute("tipos")
    public TipoImoveis[] getTipos() {
        return TipoImoveis.values();
    }

    // Listar imóveis
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("imoveis", imoveisService.listar());
        return "imoveis/listarImoveis";
    }

    // Formulário de novo imóvel
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("imovel", new Imoveis());
        return "imoveis/formImoveis";
    }

    // Salvar imóvel (novo ou editado)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Imoveis imovel) {
        imoveisService.salvar(imovel);
        return "redirect:/imoveis";
    }

    // Editar imóvel
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Imoveis imovel = imoveisService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("imovel", imovel);
        return "imoveis/formImoveis";
    }

    // Deletar imóvel
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            imoveisService.deletar(id);
            ra.addFlashAttribute("sucesso", "Imóvel excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/imoveis";
    }
}
