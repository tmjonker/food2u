package com.tmjonker.food2u.security.config;

import com.tmjonker.food2u.security.services.DatabaseUserDetailsPasswordService;
import com.tmjonker.food2u.security.services.DatabaseUserServiceDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1000)
public class SecurityConfirguation extends WebSecurityConfigurerAdapter {

    private DatabaseUserServiceDetails databaseUserDetailsService;

    private DatabaseUserDetailsPasswordService databaseUserDetailPasswordService;

    SecurityConfirguation(DatabaseUserServiceDetails databaseUserDetailsService, DatabaseUserDetailsPasswordService databaseUserDetailPasswordService) {
        this.databaseUserDetailsService = databaseUserDetailsService;
        this.databaseUserDetailPasswordService = databaseUserDetailPasswordService;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsPasswordService(
                this.databaseUserDetailPasswordService);
        provider.setUserDetailsService(this.databaseUserDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}