package src.utils;
import java.util.Scanner;

public class Scan {
  private static Scanner sc = new Scanner(System.in);

  public static String scanString() {
    String nbScan = "";
    boolean validInput = false;
    
    while (!validInput) {
      try {
        nbScan = Scan.sc.nextLine();
        validInput = true; // Exit loop if input is valid
      } catch (Exception e) {
        System.out.println("Erreur de saisie !\n\nVeuillez réessayer : ");
        sc.next(); // Clear the invalid input
      }
    }

    return nbScan;
  }

  public static int scanIntNumber() {
    int nbScan = 0;
    boolean validInput = false;
    
    while (!validInput) {
      try {
        nbScan = (int) Scan.sc.nextLong();
        validInput = true; // Exit loop if input is valid
      } catch (Exception e) {
        System.out.println("Erreur de saisie !\n\nVeuillez réessayer : ");
        sc.next(); // Clear the invalid input
      }
    }
    
    return nbScan;
  }

  public static boolean scanBoolean() {
    boolean boolScan = false;
    boolean validInput = false;
    
    while (!validInput) {
      try {
        boolScan = Scan.sc.nextBoolean();
        validInput = true; // Exit loop if input is valid
      } catch (Exception e) {
        System.out.println("Erreur de saisie !\n\nVeuillez réessayer : ");
        sc.next(); // Clear the invalid input
      }
    }
    
    return boolScan;
  }
}
