package com.curso;

public class UserService {

  private final LRUCache<String, Object> lruCache = new LRUCache<>(100);

  public void writeUserToCache(User user) {
    this.lruCache.put(user.getName(), user.getRole());
  }
}
