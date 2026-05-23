package com.curso;

import java.util.Scanner;

public class Utils {

  public final Scanner scanner = new Scanner(System.in);

  /**
   * This method is used to launch the main menu of the application.
   * It will display options to change the role or print user data.
   */
  public void launchMenu() {
    System.out.println("Main menu");
    System.out.println("1. input user data");
    System.out.println("2. print users data");
    System.out.println("0. Quit:");
    System.out.print("Select option: ");
  }

  public void processMenuOption(Users user) {
    int opt = 0;
    do {
      launchMenu();
      opt = scanner.nextInt();
      switch (opt) {
        case 1:
          System.out.println("WAIT FOR SOME TIME");
          user.readUserData();
          break;

        case 2:
          System.out.println("WAIT FOR SOME TIME");
          user.printUserData();
          break;

        default:
          break;
      }

    } while (opt != 9);
    scanner.close();
  }

}
