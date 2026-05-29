package com.curso;

public class RolNormal implements RolesInterface {

    private final RolType roleName = RolType.ROL_NORMAL;

    public RolNormal() {
    }

    @Override
    public void saludo() {
        System.out.println("Soy un usuario normal");
    }

    @Override
    public void printRoleName() {
        System.out.println("The ROL name is: " + this.roleName);
    }

}
