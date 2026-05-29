package com.curso;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class UserService {

  private final LRUCache<String, Object> lruCache;

  public UserService(int tamCache) {
    this.lruCache = new LRUCache<>(tamCache);
  }

  public void writeUserToCache(User user) {
    this.lruCache.put(user.getName(), user.getRole());
  }

  public void readUsersFromCache() {
    for (final java.util.Map.Entry<String, Object> entry : this.lruCache.entrySet()) {
      final String rol = switch (entry.getValue()) {
        case RolAdmin ra -> "Rol Administrador";
        case RolNormal rn -> "Rol Normal";
        default -> "UNKNOWN";
      };
      System.out.println("Nombre: " + entry.getKey() + " - " + rol);
    }
  }

  public User readUserDataManually() {
    final User user = new User();

    // PROCESS ROLE
    System.out.print("Enter your role (admin/normal): ");
    final String role = Utils.scanner.nextLine().trim().toLowerCase();
    user.setRole(this.checkRole(role));

    // PROCESS USERNAME
    System.out.print("Enter your user name: ");
    final String name = Utils.scanner.nextLine();
    user.setName(name);

    this.writeUserToCache(user);

    return user;
  }

  private RolesInterface checkRole(String roleaux) {
    return switch (roleaux) {
      case "admin" -> new RolAdmin();
      case "normal" -> new RolNormal();
      default -> {
        System.out.println("Invalid role. Defaulting to normal user.");
        yield new RolNormal();
      }
    };
  }

  public void readFromFile() {
    final Path path = Path.of(
            Objects.requireNonNull(
                    this.getClass().getClassLoader().getResource("users-import.users")).getPath());
    try (final BufferedReader reader = Files.newBufferedReader(path)) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String name = line.trim().substring(0, line.indexOf(":"));
        final String role = line.trim().substring(line.indexOf(":") + 1);
        System.out.println("NAME: " + name + " ROLE: " + role);
        final RolesInterface rolesInterface;
        if ("admin".equals(role)) {
          rolesInterface = new RolAdmin();
        } else {
          rolesInterface = new RolNormal();
        }
        final User user = new User(rolesInterface, name);
        this.writeUserToCache(user);
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

}
