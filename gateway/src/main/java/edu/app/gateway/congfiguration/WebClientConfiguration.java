package edu.app.gateway.congfiguration;

import edu.app.gateway.repository.IdentityClient;
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
    WebClient studentWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081/student")
                .build();
    }
// cau hinh client idendity cho gateway
    @Bean
    WebClient identityWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8081/identity")
                .build();
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

    @Bean
    IdentityClient identityClient(WebClient identityWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(identityWebClient))
                .build();
        return httpServiceProxyFactory.createClient(IdentityClient.class);
    }
    @Bean
    StudentClient studentClient(WebClient studentWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(studentWebClient))
                .build();
        return httpServiceProxyFactory.createClient(StudentClient.class);
    }
}
