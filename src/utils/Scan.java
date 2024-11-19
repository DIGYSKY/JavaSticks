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
    try {
      StringBuilder input = new StringBuilder();
      boolean reading = true;

      while (reading) {
        KeyStroke keyStroke = terminal.readInput();
        
        if (keyStroke.getKeyType() == KeyType.Enter) {
          reading = false;
        }
        else if (keyStroke.getKeyType() == KeyType.Character) {
          input.append(keyStroke.getCharacter());
          // Afficher le caractère tapé
          System.out.print(keyStroke.getCharacter());
        }
        else if (keyStroke.getKeyType() == KeyType.Backspace && input.length() > 0) {
          input.deleteCharAt(input.length() - 1);
          // Effacer le dernier caractère affiché
          System.out.print("\b \b");
        }
      }
      
      System.out.println(); // Nouvelle ligne après l'entrée
      return input.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }

  public static int scanIntNumber() {
    try {
      StringBuilder input = new StringBuilder();
      boolean reading = true;

      while (reading) {
        KeyStroke keyStroke = terminal.readInput();
        
        if (keyStroke.getKeyType() == KeyType.Enter) {
          if (input.length() > 0) { // Vérifie qu'il y a une entrée
            try {
              return Integer.parseInt(input.toString());
            } catch (NumberFormatException e) {
              System.out.println("\nErreur de saisie !\nVeuillez entrer un nombre : ");
              input.setLength(0); // Réinitialise l'entrée
            }
          }
        }
        else if (keyStroke.getKeyType() == KeyType.Character) {
          char c = keyStroke.getCharacter();
          // N'accepte que les chiffres
          if (Character.isDigit(c)) {
            input.append(c);
            System.out.print(c);
          }
        }
        else if (keyStroke.getKeyType() == KeyType.Backspace && input.length() > 0) {
          input.deleteCharAt(input.length() - 1);
          System.out.print("\b \b");
        }
      }
      
      return 0; // Valeur par défaut si quelque chose se passe mal
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
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
      if (keyStroke.getKeyType() == KeyType.Escape) return '\u001B';

      return keyStroke.getCharacter() != null ? keyStroke.getCharacter() : '\0';
    } catch (Exception e) {
      e.printStackTrace();
      return '\0';
    }
  }
}
