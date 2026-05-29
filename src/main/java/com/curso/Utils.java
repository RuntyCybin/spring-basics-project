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
  public void launchLoginMenu() {
    System.out.println("Login menu");
    System.out.println("1. input user data manually");
    System.out.println("2. print user data from cache");
    System.out.println("3. import user data from file");
    System.out.println("0. Quit:");
    System.out.print("Select option: ");
  }

  public void processMenuOption() {
    int opt;
    do {
      System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
      this.launchLoginMenu();
      opt = scanner.nextInt();
      scanner.nextLine(); // consume el \n residual del nextInt()
      System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
      switch (opt) {
        case 1:
          System.out.println(":::::::::::::::::::::::Reading a user manually::::::::::::::::::");
          final User user = this.userService.readUserDataManually();
          break;

        case 2:
          System.out.println(":::::::::::::Printing users from cache::::::::::::::::");
          this.userService.readUsersFromCache();
          break;

        case 3:
          System.out.println("::::::::::::::::Import users from file::::::::::::::::");
          this.userService.readFromFile();
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
