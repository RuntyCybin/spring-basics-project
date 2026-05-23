package com.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.RolAdmin;
import com.curso.RolNormal;
import com.curso.Users;

@Configuration
public class BeansConfiguration {

    @Bean
    public RolNormal normalRol() {
        return new RolNormal("normal");
    }

    @Bean
    public RolAdmin adminRol() {
        return new RolAdmin("admin");
    }

    @Bean
    public Users users() {
        return new Users(normalRol(), "Default User");
    }

    @Bean
    public Users adminUser() {
        return new Users(adminRol(), "Admin User");
    }

}
