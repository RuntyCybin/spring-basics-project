package com.curso.config;

import com.curso.LRUCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.RolAdmin;
import com.curso.RolNormal;
import com.curso.Users;

@Configuration
public class BeansConfiguration {

  @Value("${lrucache.capacity:100}")
  private int lruCacheCapacity;

  @Bean
  public RolNormal normalRol() {
    return new RolNormal("normal");
  }

  @Bean
  public RolAdmin adminRol() {
    return new RolAdmin("admin");
  }

  @Bean
  public Users normalUser() {
    return new Users(this.normalRol(), "Default User");
  }

  @Bean
  public Users adminUser() {
    return new Users(this.adminRol(), "Admin User");
  }

  @Bean
  public LRUCache<String, Object> lruCache() {
    return new LRUCache<>(this.lruCacheCapacity);
  }

}
