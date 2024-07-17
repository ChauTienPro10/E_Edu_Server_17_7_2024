package edu.app.gateway.congfiguration;

import edu.app.gateway.repository.StudentClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Configuration
public class WebClientConfiguration {
    @Bean
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8081/student")
                .build();
    }
    @Bean
    StudentClient studentClient(WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory=HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(StudentClient.class);
    }
    @Bean
    CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsWebFilter(source);

    }
}
