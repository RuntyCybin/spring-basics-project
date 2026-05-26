package com.curso;

public class RolAdmin implements RolesInterface {

    private final String name;

    public RolAdmin(String n) {
        this.name = n;
    }

    @Override
    public void saludo() {
        System.out.println("Soy un usuario ADMINISTRADOR!!!");
    }

    @Override
    public void printRoleName() {
        System.out.println("The ROL name is: " + this.name);
    }

}
