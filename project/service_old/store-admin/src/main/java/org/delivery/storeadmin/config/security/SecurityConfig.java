package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity // security 활성화
public class SecurityConfig {
    
    private final List<String> SWAGGER = List.of(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    );
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable);
        http
            .authorizeHttpRequests(
                auth -> {
                    auth
                        // 정적 리소스에 대한 모든 요청 허용
                        .requestMatchers(
                            PathRequest.toStaticResources().atCommonLocations()
                        ).permitAll()
                        // Swagger UI 접근 허용
                        .requestMatchers(
                            SWAGGER.toArray(new String[0])
                        ).permitAll()
                        // open-api 하위 모든 주소는 인증 없이 통과
                        .requestMatchers(
                            "/open-api/**"
                        ).permitAll()
                        // "/main" 및 "/" 경로도 인증 없이 허용
                        .requestMatchers(
                            "/", "/main"
                        ).permitAll()
                        .anyRequest().authenticated();
                } // 그 외 모든 요청은 인증 필요
            )
            .formLogin()
            .defaultSuccessUrl("/main", true); // 로그인 성공 후 "/main"으로 리디렉션
        
        return http.build();
    }

    
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//            // csrf 비활성화
//            .csrf().disable()
//            .authorizeHttpRequests(it -> {
//                it
//                    .requestMatchers(
//                        PathRequest.toStaticResources().atCommonLocations()
//                    ).permitAll()   // 정적 리소스에 대한 모든 요청 허용
//
//                    // Swagger 는 인증 없이 통과
//                    .mvcMatchers(
//                        SWAGGER.toArray(new String[0])
//                    ).permitAll()
//
//                    // 그 외 모든 요청은 인증 사용
//                    .anyRequest().authenticated();
//            })
//            .formLogin(Customizer.withDefaults());
//
//        return httpSecurity.build();
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // hash 방식으로 암호화 -> 인코딩 O, 디코딩 X
        return new BCryptPasswordEncoder();
    }
}
