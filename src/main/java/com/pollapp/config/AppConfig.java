package com.pollapp.config;

import com.pollapp.entity.wrapper.CustomUserDetails;
import com.pollapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import javax.validation.Validator;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pollapp"})
@EnableScheduling
public class AppConfig extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfig.class);
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserAccountRepository repo) throws Exception {
        builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
    }
}