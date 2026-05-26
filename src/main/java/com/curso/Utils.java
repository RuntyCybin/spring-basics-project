package com.curso;

import java.util.Scanner;

public class Utils {

  public static final Scanner scanner = new Scanner(System.in);
  public UserService userService;

  public Utils(UserService service) {
    this.userService = service;
  }

  /**
   * This method is used to launch the main menu of the application.
   * It will display options to change the role or print user data.
   */
  public void launchMenu() {
    System.out.println("Main menu");
    System.out.println("1. input user data");
    System.out.println("2. print user data");
    System.out.println("0. Quit:");
    System.out.print("Select option: ");

  }

  public void processMenuOption(User user) {
    int opt = 0;
    do {
      System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
      this.launchMenu();
      opt = scanner.nextInt();
      scanner.nextLine(); // consume el \n residual del nextInt()
      System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
      switch (opt) {
        case 1:
          System.out.println("Processing a user");
          user = this.userService.readUserData();
          break;

        case 2:
          System.out.println("Printing a user");
          user.printUserData();
          break;

        default:
          System.out.println("Exiting application...");
          opt = 0;
          break;
      }

    } while (opt != 0);
    scanner.close();
  }

}
