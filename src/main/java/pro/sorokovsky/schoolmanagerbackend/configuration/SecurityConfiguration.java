package pro.sorokovsky.schoolmanagerbackend.configuration;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pro.sorokovsky.schoolmanagerbackend.configurer.JwtAuthenticationConfigurer;
import pro.sorokovsky.schoolmanagerbackend.factory.DefaultAccessTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.factory.DefaultRefreshTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.point.UnauthenticatedEntryPoint;
import pro.sorokovsky.schoolmanagerbackend.service.AuthorizationService;
import pro.sorokovsky.schoolmanagerbackend.service.JwtUserDetailsService;
import pro.sorokovsky.schoolmanagerbackend.service.UsersService;
import pro.sorokovsky.schoolmanagerbackend.storage.BearerTokenStorage;
import pro.sorokovsky.schoolmanagerbackend.storage.CookieTokenStorage;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(
            @NonNull HttpSecurity http,
            JwtAuthenticationConfigurer jwtAuthenticationConfigurer,
            UnauthenticatedEntryPoint unauthenticatedEntryPoint
    ) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                .requestMatchers("/authorization/login", "/authorization/register").anonymous()
                .anyRequest().authenticated()
        );
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(unauthenticatedEntryPoint)
        );
        http.apply(jwtAuthenticationConfigurer);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthorizationService authorizationService(
            UsersService usersService,
            DefaultAccessTokenFactory accessTokenFactory,
            DefaultRefreshTokenFactory refreshTokenFactory,
            CookieTokenStorage refreshTokenStorage,
            BearerTokenStorage accessTokenStorage,
            PasswordEncoder passwordEncoder
    ) {
        return AuthorizationService
                .builder()
                .usersService(usersService)
                .accessTokenFactory(accessTokenFactory)
                .refreshTokenFactory(refreshTokenFactory)
                .refreshTokenStorage(refreshTokenStorage)
                .accessTokenStorage(accessTokenStorage)
                .passwordEncoder(passwordEncoder)
                .build();
    }

    @Bean
    public JwtAuthenticationConfigurer jwtAuthenticationConfigurer(
            JwtUserDetailsService jwtUserDetailsService,
            UnauthenticatedEntryPoint unauthenticatedEntryPoint,
            BearerTokenStorage accessTokenStorage,
            CookieTokenStorage refreshTokenStorage,
            DefaultAccessTokenFactory accessTokenFactory,
            DefaultRefreshTokenFactory refreshTokenFactory,
            UsersService usersService
    ) {
        return JwtAuthenticationConfigurer
                .builder()
                .authenticationEntryPoint(unauthenticatedEntryPoint)
                .accessTokenFactory(accessTokenFactory)
                .refreshTokenFactory(refreshTokenFactory)
                .jwtUserDetailsService(jwtUserDetailsService)
                .accessTokenStorage(accessTokenStorage)
                .refreshTokenStorage(refreshTokenStorage)
                .usersService(usersService)
                .build();
    }
}
