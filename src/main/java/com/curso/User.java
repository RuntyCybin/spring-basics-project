package com.curso;

public class User implements UsersInterface {
  /**
   * ATTRIBUTES
   */
  private RolesInterface role;
  private String name;
  private String password;

  /**
   * CONSTRUCTORS
   */
  public User() {
  }

  public User(RolesInterface r, String n) {
    this.role = r;
    this.name = n;
  }

  /**
   * METHOD IMPLEMENTS
   */
  @Override
  public void printUserData() {
    System.out.println("The user name is: " + this.getName());
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

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
