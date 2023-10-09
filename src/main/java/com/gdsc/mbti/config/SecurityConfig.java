//package com.gdsc.mbti.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class SecurityConfig {
//    private final JWTRequestFilter jwtRequestFilter;
//
//    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
//        this.jwtRequestFilter = jwtRequestFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().configurationSource(corsConfigurationSource())
//                .and()
//                .csrf().disable().headers().frameOptions().disable()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable()
//
//                .authorizeRequests()
//                .requestMatchers ("/api/**", "/login/**", "/oauth2/**").permitAll()
//                .and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//
//        return http.build();
//    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.addAllowedOriginPattern("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
//}
