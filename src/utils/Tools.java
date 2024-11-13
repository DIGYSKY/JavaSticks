package src.utils;

import java.util.Scanner;

public class Tools {
  private static Scanner sc = new Scanner(System.in);

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void sleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void sleepKeyboard() {
    System.out.println("Appuyez sur entrée pour continuer...");
    sc.nextLine();
  }
}
