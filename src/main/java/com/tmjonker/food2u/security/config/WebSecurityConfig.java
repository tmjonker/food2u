package com.tmjonker.food2u.security.config;

import com.tmjonker.food2u.security.services.DatabaseUserDetailsPasswordService;
import com.tmjonker.food2u.security.services.DatabaseUserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DatabaseUserServiceDetails userDetailsService;

    private DatabaseUserDetailsPasswordService passwordService;

    // Factory for connection to persistent_logins table.
    private DataSource dataSource;

    @Lazy
    @Autowired
    public WebSecurityConfig(DatabaseUserServiceDetails userDetailsService, DatabaseUserDetailsPasswordService passwordService,
                             DataSource dataSource) {

        this.userDetailsService = userDetailsService;
        this.passwordService = passwordService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Allow anyone to access the page resources as well as the home page / sign up page.
                .authorizeRequests()
                .antMatchers("/"
                        , "/home"
                        , "/restaurants"
                        , "/restaurants/*"
                        , "/sign-up"
                        , "/css/style.css"
                        , "/scripts/*"
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
                .tokenValiditySeconds(24 * 60 * 60) // remember-me token is valid for 24 days.
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

    // sets up authentication provider for authenticating user passwords.
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsPasswordService(
                this.passwordService);
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }

    // Sets up the password encoder used to encrypt passwords for the users.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures custom userDetailsService to be used for authentication.
    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        super.setApplicationContext(context);
        AuthenticationManagerBuilder globalAuthBuilder = context
                .getBean(AuthenticationManagerBuilder.class);
        try {
            globalAuthBuilder.userDetailsService(userDetailsService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}