package com.curso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class UserService {

  private final LRUCache<String, Object> lruCache;

  public UserService(int tamCache) {
    this.lruCache = new LRUCache<>(tamCache);
  }

  public void writeUserToCache(User user) {
    this.lruCache.put(user.getName(), user);
  }

  /**
  * READ A USERS FROM CACHE
  * iterates and prints the whole cache
  */
  public void readUsersFromCache() {
    for (final Map.Entry<String, Object> entry : this.lruCache.entrySet()) {
      final User user = (User) entry.getValue();
      user.printUserData();
    }
  }

  /**
  * READ USER FROM CONSOLE
  * input all user data manually from console
  */
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

    // PROCESS PASSWORD
    System.out.print("Enter your password: ");
    final String pwd = Utils.scanner.nextLine();
    user.setPassword(pwd);

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

  /**
  * READ USERS FROM A FILE
  * reads a file with '.users' extension line by line
  */
  public void readFromFile() {
    final Path path = Path.of(
            Objects.requireNonNull(
                    this.getClass().getClassLoader().getResource("users-import.users")).getPath());
    try (final BufferedReader reader = Files.newBufferedReader(path)) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String[] fields = line.split(":");

        System.out.println("NAME: " + fields[0] + " PWD: " + fields[1] + " ROLE: " + fields[2]);

        final RolesInterface rolesInterface;
        if ("admin".equals(fields[2])) {
          rolesInterface = new RolAdmin();
        } else {
          rolesInterface = new RolNormal();
        }
        final User user = new User();
        user.setName(fields[0]);
        user.setPassword(fields[1]);
        user.setRole(rolesInterface);

        // write User to cache
        this.writeUserToCache(user);
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * WRITE USERS FROM CACHE TO FILE
   * */
  public void writeUsersToFile() {
    final Path path = Path.of(
            Objects.requireNonNull(
                    this.getClass().getClassLoader().getResource("users-import.users")).getPath());

    try (final BufferedWriter writer = Files.newBufferedWriter(path)) {
      for (final Map.Entry<String, Object> entry : this.lruCache.entrySet()) {
        final User user = (User) entry.getValue();
        final String cadena = user.getName() + ":" + user.getPassword() + ":" + user.getRole();
        writer.write(cadena);
        writer.newLine();
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

}
