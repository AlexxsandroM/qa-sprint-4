package br.com.fiap.eficientiza_challenge_03.Security;

import br.com.fiap.eficientiza_challenge_03.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService  customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;  // Renomeado para evitar conflito
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Permite acesso a todas as rotas de login e logout
                .requestMatchers("/login", "/logout", "/error", "/css/**", "/js/**").permitAll()
                .requestMatchers("/home").permitAll()
                // Liberar todas as rotas de /vagas e /motos, incluindo sub-rotas
                .requestMatchers("/vagas/**").permitAll()  // Permite todas as rotas dentro de /vagas
                .requestMatchers("/motos/**").permitAll()  // Permite todas as rotas dentro de /motos
                // Permite acesso ao administrador a todas as rotas
                .requestMatchers("/**").hasRole("ADMIN") // ADMIN pode acessar qualquer rota
                // Restringe OPERADOR para algumas rotas
                .requestMatchers("/historicos-moto", "/historicos-moto/novo", "/funcionarios", "/funcionarios/novo").hasRole("ADMIN")
                // Resto das rotas liberadas para ADMIN e OPERADOR
                .anyRequest().hasRole("OPERADOR")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }
}
