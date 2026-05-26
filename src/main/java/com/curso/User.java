package com.curso;

public class User implements UsersInterface {
  private RolesInterface role;
  private String name;

  public User() {
  }

  public User(RolesInterface ri, String n) {
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
