package br.com.fiap.eficientiza_challenge_03.controller;
import br.com.fiap.eficientiza_challenge_03.model.Usuario;
import br.com.fiap.eficientiza_challenge_03.service.UsuarioServiceMottu;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceMottu usuarioServiceMottu;

    public UsuarioController(UsuarioServiceMottu usuarioServiceMottu) {
        this.usuarioServiceMottu = usuarioServiceMottu;
    }

    // Listagem
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServiceMottu.listar();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listagem";
    }

    // Formulário novo
    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    // Salvar
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute @Valid Usuario usuario,
                                org.springframework.validation.BindingResult binding,
                                Model model) {
        if (binding.hasErrors()) {
            return "usuarios/formulario";
        }
        usuarioServiceMottu.salvar(usuario);
        return "redirect:/usuarios";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServiceMottu.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    // Alterar
    @PostMapping("/alterar/{id}")
    public String alterarUsuario(@PathVariable Long id,
                                 @ModelAttribute @Valid Usuario usuario,
                                 org.springframework.validation.BindingResult binding,
                                 Model model) {
        if (binding.hasErrors()) {
            return "usuarios/formulari";
        }
        usuarioServiceMottu.atualizar(id, usuario);
        return "redirect:/usuarios";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioServiceMottu.excluir(id);
        return "redirect:/usuarios";
    }
}
