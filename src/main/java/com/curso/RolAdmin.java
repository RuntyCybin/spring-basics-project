package com.curso;

public class RolAdmin implements RolesInterface {

  private final RolType roleName = RolType.ROL_ADMIN;

  public RolAdmin() {
  }

  @Override
  public void saludo() {
    System.out.println("Soy un usuario ADMINISTRADOR!!!");
  }

  @Override
  public void printRoleName() {
    System.out.println("The ROL name is: " + this.roleName);
  }

}
