package br.com.fiap.eficientiza_challenge_03.service;

import br.com.fiap.eficientiza_challenge_03.model.Usuario;
import br.com.fiap.eficientiza_challenge_03.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscando o usuário no banco de dados
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Retorna o UserDetails com a senha (sem criptografia por enquanto)
        return User.builder()
                .username(usuario.getEmail())  // E-mail como nome de usuário
                .password(usuario.getSenha())  // Senha não criptografada (para teste)
                .roles(usuario.getTipo())      // Tipo de usuário (admin, comum, etc)
                .build();
    }

    // Método para salvar o usuário (sem criptografia da senha)
    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);  // Senha será salva como está, sem criptografia
    }
}
