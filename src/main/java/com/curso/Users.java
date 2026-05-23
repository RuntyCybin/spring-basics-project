package com.curso;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Reading user data...");

        System.out.println("Please enter your role (admin/normal): ");
        String role = scanner.nextLine().trim().toLowerCase();
        if (role.equals("admin")) {
            setRole(new RolAdmin(role));
        } else if (role.equals("normal")) {
            setRole(new RolNormal(role));
        } else {
            System.out.println("Invalid role. Defaulting to normal user.");
            setRole(new RolNormal("normal"));
        }
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        setName(name);

        this.printUserame();
        this.getRole().saludo();
    }

    /**
     * GETTERS AND SETTERS
     */
    public RolesInterface getRole() {
        return role;
    }

    public void setRole(RolesInterface role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
