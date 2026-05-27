package com.curso;

public class UserService {

  private final LRUCache<String, Object> lruCache;

  public UserService(int tamCache) {
    this.lruCache = new LRUCache<>(tamCache);
  }

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
}
