package com.mahdi.sesootservice.security;

import com.mahdi.sesootservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class AppSecurity{
    private final PersonService personService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(path -> path.requestMatchers("/user/putorder", "/user/profile").hasRole("user"))
//                .authorizeHttpRequests(path -> path.anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(email -> personService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email+ "not found")))
                .passwordEncoder(passwordEncoder);
    }


}
