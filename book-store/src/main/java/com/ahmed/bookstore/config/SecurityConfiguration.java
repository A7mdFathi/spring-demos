package com.ahmed.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.AllArgsConstructor;

import static com.ahmed.bookstore.user.Permission.ADMIN_CREATE;
import static com.ahmed.bookstore.user.Permission.ADMIN_DELETE;
import static com.ahmed.bookstore.user.Permission.ADMIN_READ;
import static com.ahmed.bookstore.user.Permission.ADMIN_UPDATE;
import static com.ahmed.bookstore.user.Permission.MANAGER_CREATE;
import static com.ahmed.bookstore.user.Permission.MANAGER_DELETE;
import static com.ahmed.bookstore.user.Permission.MANAGER_READ;
import static com.ahmed.bookstore.user.Permission.MANAGER_UPDATE;
import static com.ahmed.bookstore.user.Role.ADMIN;
import static com.ahmed.bookstore.user.Role.MANAGER;

// @EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

        private static final String[] WHITE_LIST_URL = {
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html" };

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authenticationProvider;
        private final LogoutHandler logoutHandler;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
           http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL)
                                        .permitAll()
                                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                                        .requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                        .requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                        /// for admin
                                        /*
                                         * .requestMatchers("/api/v1/admin/**").hasAnyRole(ADMIN.name())
                                         * .requestMatchers(HttpMethod.GET, "/api/v1/admin/**")
                                         * .hasAnyAuthority(ADMIN_READ.name())
                                         * .requestMatchers(HttpMethod.POST, "/api/v1/admin/**")
                                         * .hasAnyAuthority(ADMIN_CREATE.name())
                                         * .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**")
                                         * .hasAnyAuthority(ADMIN_UPDATE.name())
                                         * .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**")
                                         * .hasAnyAuthority(ADMIN_DELETE.name())
                                         */
                                        .anyRequest()
                                        .authenticated())
                        .sessionManagement(session -> session
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                                        .addLogoutHandler(logoutHandler)
                                        .logoutSuccessHandler(
                                                        (request,response,authentication) -> SecurityContextHolder.clearContext())

                                );

                return http.build();
        }

        // @Bean
        // public InMemoryUserDetailsManager userDetailsManager() {

        // UserDetails ahmed = User.builder()
        // .username("ahmed")
        // .password("{noop}test123")
        // .roles("EMPLOYEE", "MANAGER", "ADMIN")
        // .build();

        // return new InMemoryUserDetailsManager(ahmed);

        // }
}
