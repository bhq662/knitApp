package hh.harjoitustyo.knitapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                // Kaikille avoimet polut
                .requestMatchers("/", "/index", "/login", "/css/**",
                        "/projects", "/yarns", "/viewproject/**", "/viewyarn/**")
                .permitAll()

                // Suojatut polut
                .requestMatchers("/newproject", "/editproject/**", "/deleteproject/**",
                        "/newyarn", "/edityarn/**", "/deleteyarn/**")
                .authenticated()

                // Kaikki muu vaatii kirjautumisen
                .anyRequest().authenticated())

                // Kirjautumissivu
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)) // ohjaa etusivulle kirjautumisen jälkeen

                // Uloskirjautuminen
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        var user = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user);
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}