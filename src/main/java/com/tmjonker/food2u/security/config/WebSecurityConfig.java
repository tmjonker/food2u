package com.tmjonker.food2u.security.config;

import com.tmjonker.food2u.security.services.DatabaseUserDetailsPasswordService;
import com.tmjonker.food2u.security.services.DatabaseUserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DatabaseUserServiceDetails databaseUserDetailsService;

    @Autowired
    private DatabaseUserDetailsPasswordService databaseUserDetailPasswordService;

    // Factory for connection to database.
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Allow anyone to access the page resources as well as the home page / sign up page.
                .authorizeRequests()
                .antMatchers("/"
                        , "/home"
                        , "/sign-up"
                        , "/css/style.css"
                        , "/scripts/scripts.js"
                        , "/images/*").permitAll()
                .anyRequest().authenticated()
                .and()
                // Sets up login page
                .formLogin()
                .loginPage("/sign-in")
                .permitAll()
                .defaultSuccessUrl("/welcome", true)
                .and()
                // Adds remember me functionality to sign in's.
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60)
                .rememberMeParameter("remember-me")
                .and()
                .httpBasic()
                .and()
                // Sets up log out functionality.
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/sign-in?logout")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                // disables CRSF protection to allow logout to be completed by a GET request.
                .csrf().disable();
    }

    // Repository responsible for updating persistent_logins table in sql database.
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
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

    // Sets up the password encoder used to encrypt passwords for the users.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}