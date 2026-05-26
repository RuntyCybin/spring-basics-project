package com.curso.config;

import com.curso.LRUCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.RolAdmin;
import com.curso.RolNormal;
import com.curso.User;

@Configuration
public class BeansConfiguration {

  @Value("${lrucache.capacity:100}")
  private int lruCacheCapacity;

  @Bean
  public RolNormal normalRol() {
    return new RolNormal();
  }

  @Bean
  public RolAdmin adminRol() {
    return new RolAdmin();
  }

  @Bean
  public User normalUser() {
    return new User(this.normalRol(), "Default User");
  }

  @Bean
  public User adminUser() {
    return new User(this.adminRol(), "Admin User");
  }

  @Bean
  public LRUCache<String, Object> lruCache() {
    return new LRUCache<>(this.lruCacheCapacity);
  }

}
