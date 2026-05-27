package com.curso;

import org.springframework.beans.factory.annotation.Value;

public class UserService {

  @Value("${lrucache.capacity:100}")
  private int lruCacheCapacity;

  private final LRUCache<String, Object> lruCache = new LRUCache<>(this.lruCacheCapacity);

  public void writeUserToCache(User user) {
    this.lruCache.put(user.getName(), user.getRole());
  }

  public void readUsersCache() {
    for (final java.util.Map.Entry<String, Object> entry : this.lruCache.entrySet()) {
      final String rol = switch (entry.getValue()) {
        case RolAdmin ra -> "Rol Administrador";
        case RolNormal rn -> "Rol Normal";
        default -> "UNKNOWN";
      };
      System.out.println(entry.getKey() + " - " + rol);
    }
  }

  public User readUserData() {
    final User user = new User();

    // PROCESS ROLE
    System.out.print("Enter your role (admin/normal): ");
    final String role = Utils.scanner.nextLine();
    final String roleaux = role.trim().toLowerCase();

    if (roleaux.equals("admin")) {
      user.setRole(new RolAdmin());
    } else if (roleaux.equals("normal")) {
      user.setRole(new RolNormal());
    } else {
      System.out.println("Invalid role. Defaulting to normal user.");
      user.setRole(new RolNormal());
    }

    // PROCESS USERNAME
    System.out.print("Enter your user name: ");
    final String name = Utils.scanner.nextLine();
    user.setName(name);

    this.writeUserToCache(user);

    return user;
  }
}
