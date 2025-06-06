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

import br.com.imobiliaria.model.Corretores;
import br.com.imobiliaria.repository.CorretoresRepository;
import br.com.imobiliaria.service.CorretoresService;

@Controller
@RequestMapping("/corretores")
public class CorretoresController {

   // @Autowired
   // private CorretoresRepository corretoresRepository;
    @Autowired
    private CorretoresService corretoresService;

    // Listar corretores
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("corretores", corretoresService.listar());
        return "corretores/listarCorretores";
    }

    // Formulário de novo corretor
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("corretor", new Corretores());
        return "corretores/formCorretores";
    }

    // Salvar corretor (novo ou editado)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Corretores corretor) {
    	corretoresService.salvar(corretor);
        return "redirect:/corretores";
    }

    // Editar corretor
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Corretores corretor = corretoresService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("corretor", corretor);
        return "corretores/formCorretores";
    }

    // Deletar corretor
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
        	corretoresService.deletar(id);
            ra.addFlashAttribute("sucesso", "Corretor excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/corretores";
    }
    
    
}

