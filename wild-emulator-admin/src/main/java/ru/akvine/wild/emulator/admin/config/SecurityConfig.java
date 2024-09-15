package ru.akvine.wild.emulator.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import ru.akvine.wild.emulator.admin.config.security.RestAuthenticationEntryPoint;
import ru.akvine.wild.emulator.admin.config.security.RestSuccessLogoutHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${spring.session.cookie-name}")
    private String cookieName;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/security/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(c -> c.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
                .logout(logout -> {
                    logout.deleteCookies(cookieName);
                    logout.logoutSuccessHandler(restSuccessLogoutHandler());
                    logout.invalidateHttpSession(true);
                });
        return http.build();
    }

    @Bean
    public RestSuccessLogoutHandler restSuccessLogoutHandler() {
        return new RestSuccessLogoutHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }
}
