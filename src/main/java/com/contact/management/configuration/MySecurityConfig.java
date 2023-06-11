package com.contact.management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //this is an example of basic authentication
        http.
                authorizeRequests()
                .antMatchers("/contacts/**").hasRole("ADMIN")
                .antMatchers("/contacts/**").hasRole("NORMAL")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }
    //Role--High level overview--Normal--READ
    //Authority-- Permissions-- READ\
    //ADMIN--- READ , WRITE , UPDATE
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("FirstUser").password(this.passwordEncoder().encode("123@abc")).roles("NORMAL");
        auth.inMemoryAuthentication().withUser("FirstAdmin").password(this.passwordEncoder().encode("321@abd")).roles("ADMIN");
    }
    //this is used as we cannot send plain text password this will encode the password.
    @Bean
    public PasswordEncoder passwordEncoder(){
       // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(10);
    }

}
