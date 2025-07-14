package br.edu.iftm.vitrino.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta é uma classe de configuração do Spring
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a configuração a todos os endpoints da sua API
                .allowedOrigins("http://localhost:4200", "http://seu-dominio-frontend.com") // Permite acesso apenas destas origens
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // Permite estes métodos HTTP
                .allowedHeaders("*") // Permite todos os cabeçalhos na requisição
                .allowCredentials(true) // Permite o envio de credenciais (cookies, tokens de autorização, etc.)
                .maxAge(3600); // Define por quanto tempo (em segundos) o navegador pode armazenar em cache os resultados da pré-verificação do CORS
    }
}