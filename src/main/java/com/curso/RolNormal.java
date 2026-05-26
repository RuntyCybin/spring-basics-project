package com.curso;

public class RolNormal implements RolesInterface {

    private final String name = "normal";

    public RolNormal() {
    }

    @Override
    public void saludo() {
        System.out.println("Soy un usuario normal");
    }

    @Override
    public void printRoleName() {
        System.out.println("The ROL name is: " + this.name);
    }

}
