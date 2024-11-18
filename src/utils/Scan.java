package src.utils;

import java.util.Scanner;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Scan {
  private static Scanner sc = new Scanner(System.in);
  private static Terminal terminal;
  
  static {
    try {
      terminal = new DefaultTerminalFactory().createTerminal();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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

  public static char scanKey() {
    try {
      KeyStroke keyStroke = terminal.readInput();

      if (keyStroke.getKeyType() == KeyType.ArrowUp) return 'z';
      if (keyStroke.getKeyType() == KeyType.ArrowDown) return 's';
      if (keyStroke.getKeyType() == KeyType.ArrowLeft) return 'q';
      if (keyStroke.getKeyType() == KeyType.ArrowRight) return 'd';
      if (keyStroke.getKeyType() == KeyType.Enter) return '\n';

      return keyStroke.getCharacter() != null ? keyStroke.getCharacter() : '\0';
    } catch (Exception e) {
      e.printStackTrace();
      return '\0';
    }
  }
}
