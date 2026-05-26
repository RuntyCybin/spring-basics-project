package com.curso;

public class Users implements UsersInterface {

  private RolesInterface role;
  private String name;

  public Users() {
  }

  public Users(RolesInterface ri, String n) {
    this.role = ri;
    this.name = n;
  }

  @Override
  public void printUserame() {
    System.out.println("The user name is: " + this.getName());
  }

  @Override
  public void printUserData() {
    this.printUserame();
    this.getRole().printRoleName();
  }

  public void readUserData() {
    System.out.println("Reading user data...");

    System.out.println("Please enter your role (admin/normal): ");
    final String role = Utils.scanner.nextLine().trim().toLowerCase();
    if (role.equals("admin")) {
      this.setRole(new RolAdmin(role));
    } else if (role.equals("normal")) {
      this.setRole(new RolNormal(role));
    } else {
      System.out.println("Invalid role. Defaulting to normal user.");
      this.setRole(new RolNormal("normal"));
    }
    System.out.print("Enter your user name: ");
    final String name = Utils.scanner.nextLine();
    this.setName(name);

    this.printUserame();
    this.getRole().saludo();
  }

  /**
   * GETTERS AND SETTERS
   */
  public RolesInterface getRole() {
    return this.role;
  }

  public void setRole(RolesInterface role) {
    this.role = role;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
