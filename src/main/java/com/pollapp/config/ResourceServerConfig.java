package com.pollapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResourceServerConfig() {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*http.requestMatchers()
                .antMatchers("/private")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll();*/
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/private/**").authenticated()
                .and().formLogin().loginPage("/")
                //.defaultSuccessUrl("/", true)
                //.failureUrl("/t/f")
                .permitAll()
        .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
                .userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
