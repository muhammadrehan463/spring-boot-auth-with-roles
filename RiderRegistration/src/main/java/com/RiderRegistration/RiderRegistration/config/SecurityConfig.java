package com.RiderRegistration.RiderRegistration.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource) {
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        theUserDetailsManager
                .setUsersByUsernameQuery("select email, password from users where email=?");

        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select email, role from users where email=?");

        return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/riders/get-rider").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/riders/get-riderbyid").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/riders/create-rider").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/riders/update-rider").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/riders/delete-rider/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated());

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // use http basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable cross site request (csrf)
        http.csrf(csrf->csrf.disable());

        return http.build();
    }
}
