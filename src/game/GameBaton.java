package src.game;

import src.game.Players.Player;
import src.utils.Scan;
import src.utils.Tools;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameBaton {
  private Player player1;
  private Player player2;
  private String version = "Origin";
  private int currentPlayer = 1;
  public int batonsDefault = 20;
  private ArrayList<Boolean> batonsList = new ArrayList<>();
  private int[] lastBatonsTaken = new int[3];

  public GameBaton() {
    String[] playerData = this.loadSave();
    if (playerData != null) {
      Tools.clearConsole();
      System.out.println("Sauvegarde trouvée !");
      System.out.println("Voulez-vous charger la sauvegarde ? (o/n)");
      while (true) {
        char input = Scan.scanKey();
        if (input == 'o' || input == '\u001B') {
          Tools.clearConsole();
          System.out.println("Chargement de la sauvegarde...");
          this.player1 = new Player(playerData[0]);
          this.player1.setScore(Integer.parseInt(playerData[1]));
          this.player1.setLevel(Integer.parseInt(playerData[2]));
          this.player1.setIsComputer(Boolean.parseBoolean(playerData[3]));
          this.player1.setGame(this);
          this.player2 = new Player(playerData[4]);
          this.player2.setScore(Integer.parseInt(playerData[5]));
          this.player2.setLevel(Integer.parseInt(playerData[6]));
          this.player2.setIsComputer(Boolean.parseBoolean(playerData[7]));
          this.player2.setGame(this);
          Tools.clearConsole();
          System.out.println("Sauvegarde chargée !");
          break;
        } else if (input == 'n') {
          break;
        }
      }
    }

    this.initGame();
  }

  private boolean saveGame() {
    File saveDir = new File("save");
    if (!saveDir.exists()) {
      saveDir.mkdir();
    }

    try (FileWriter writer = new FileWriter("save/game-card-1.save")) {
      writer.write(player1.getName() + "\n");
      writer.write(player1.getScore() + "\n");
      writer.write(player1.getLevel() + "\n");
      writer.write(player1.isComputer() + "\n");
      writer.write(player2.getName() + "\n");
      writer.write(player2.getScore() + "\n");
      writer.write(player2.getLevel() + "\n");
      writer.write(player2.isComputer() + "\n");
      writer.write(this.version + "\n");
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  private String[] loadSave() {
    File saveFile = new File("save/game-card-1.save");
    if (!saveFile.exists()) {
      return null;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
      String[] playerData = new String[9];
      for (int i = 0; i < 9; i++) {
        playerData[i] = reader.readLine();
      }

      return playerData;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private void initGame() {
    Tools.clearConsole();

    if (this.player1 != null && this.player2 != null) {
      this.resetGame();
    }

    System.out.println("        Jeu du baton " + this.version);
    System.out.println();
    System.out.println(
        "Le jeu du baton est un jeu de logique qui consiste retirer des batons de la table.\n Le but du jeu est de ne pas retirer le dernier baton de la table.\n");
    System.out.println("Jouez-vous seul ou avec un autre joueur ?");

    String input = Scan.scanString().toLowerCase();
    boolean isSolo = input.contains("seul") || input.contains("solo") || input.contains("oui");

    if (isSolo) {
      Tools.clearConsole();
      System.out.println("Entrez votre nom : ");
      player1 = new Player(Scan.scanString());

      player2 = new Player();

      System.out.println("Les joueurs sont : " + player1.getName() + " et " + player2.getName());
    } else {
      Tools.clearConsole();
      System.out.println("Entrez le nom du premier joueur : ");
      player1 = new Player(Scan.scanString());

      Tools.clearConsole();
      System.out.println("Entrez le nom du deuxième joueur : ");
      player2 = new Player(Scan.scanString());

      System.out.println("Les joueurs sont : " + player1.getName() + " et " + player2.getName());
    }

    this.player1.setGame(this);
    this.player2.setGame(this);

    this.saveGame();

    this.resetGame();
  }

  private void resetGame() {
    this.batonsList.clear();

    for (int i = 0; i < this.batonsDefault; i++) {
      this.batonsList.add(true);
    }

    this.gameLoop();
  }

  private void mainMenu() {
    Tools.clearConsole();

    System.out.println("        Menu Baton " + this.version);
    System.out.println();
    System.out.println("Niveaux :");
    System.out.println("  " + this.player1.getName() + " : " + this.player1.getLevel());
    System.out.println("  " + this.player2.getName() + " : " + this.player2.getLevel());
    System.out.println();
    System.out.println("Scores :");
    System.out.println("  " + this.player1.getName() + " : " + this.player1.getScore());
    System.out.println("  " + this.player2.getName() + " : " + this.player2.getScore());
    System.out.println();
    System.out.println("1. Rejouer");
    System.out.println("2. Quitter");

    while (true) {
      char input = Scan.scanKey();

      if (input == '1' || input == '\u001B') {
        this.initGame();
      } else if (input == '2') {
        System.out.println("Au revoir !");
        System.exit(0);
      }
    }
  }

  public void menuInGame() {
    Tools.clearConsole();

    System.out.println("        Menu Baton " + this.version);
    System.out.println();
    System.out.println("Niveaux :");
    System.out.println("  " + this.player1.getName() + " : " + this.player1.getLevel());
    System.out.println("  " + this.player2.getName() + " : " + this.player2.getLevel());
    System.out.println();
    System.out.println("Scores :");
    System.out.println("  " + this.player1.getName() + " : " + this.player1.getScore());
    System.out.println("  " + this.player2.getName() + " : " + this.player2.getScore());
    System.out.println();
    System.out.println("1. Reprendre la partie");
    System.out.println("2. Quitter");

    while (true) {
      char input = Scan.scanKey();
      if (input == '1' || input == '\u001B') {
        this.displayGame();
        break;
      } else if (input == '2') {
        System.exit(0);
      }
    }
  }

  public boolean isGameOver() {
    return this.getBatons() <= 0;
  }

  private void gameLoop() {
    while (true) {
      if (this.isGameOver())
        break;

      this.displayGame();

      if (this.currentPlayer == 1) {
        this.player1.gamePlay();
      } else {
        this.player2.gamePlay();
      }

      this.currentPlayer = (this.currentPlayer == 1) ? 2 : 1;
    }

    this.endGame();
  }

  public void displayGame() {
    this.displayGame(this.currentPlayer == 1 ? this.player1.getPosition() : (this.player2.isComputer() ? 0 : this.player2.getPosition()));
  }

  public void displayGame(int position) {
    Tools.clearConsole();
    System.out.println("        Baton " + this.version);
    System.out.println();
    System.out.println("Appuyez sur - Echap - pour mettre en pause");
    System.out.println();
    System.out.println("Tour du joueur " + (this.currentPlayer == 1 ? this.player1.getName() : this.player2.getName()));
    System.out.println();
    System.out.println("Derniers batons retirés : " + this.lastBatonsTaken[0] + " - " + this.lastBatonsTaken[1] + " - " + this.lastBatonsTaken[2]);
    System.out.println();

    String batonsDisplay = "";
    String batonsPosition = "";

    for (int i = 0; i < this.batonsList.size(); i++) {
      batonsDisplay += this.batonsList.get(i) ? "|   " : "    ";
      batonsPosition += (i + 1) == position ? "X   " : "    ";
    }

    System.out.println(batonsDisplay);
    System.out.println(batonsPosition);
    System.out.println();
  }

  private void endGame() {

    Tools.clearConsole();
    System.out.println("        Jeu du baton");

    System.out.println("Le jeu est terminé !");

    if (this.currentPlayer == 1) {
      this.player1.scoreUp();

      System.out.println("Le joueur " + this.player1.getName() + " a gagné !");

      if (this.player2.isComputer() && this.player1.levelUp()) {
        this.player2.levelUp();
      }
    } else {
      this.player2.scoreUp();

      System.out.println("Le joueur " + this.player2.getName() + " a gagné !");

      if (!this.player2.isComputer()) {
        this.player2.levelUp();
      }
    }

    this.saveGame();

    System.out.println("Les scores finaux sont : " + this.player1.getName() + " : " + this.player1.getScore() + " et "
        + this.player2.getName() + " : " + this.player2.getScore());
    Tools.sleepKeyboard();
    Tools.clearConsole();

    this.mainMenu();
  }

  public boolean takeBaton(int position) {
    boolean success = false;
    try {
      success = this.batonsList.get(position - 1);
      this.batonsList.set(position - 1, false);
      return success;
    } catch (Exception e) {
      return false;
    }
  }

  public void takeBatonComputer(int index) {
    int randomPosition;
    int attempts = 0;
    int maxAttempts = this.batonsList.size();

    do {
      randomPosition = (int) (Math.random() * this.batonsList.size());
      attempts++;
    } while (!this.batonsList.get(randomPosition) && attempts < maxAttempts);

    if (this.batonsList.get(randomPosition)) {
      this.batonsList.set(randomPosition, false);
      this.lastBatonsTaken[index] = randomPosition + 1;
      this.displayGame();
      System.out.println("L'ordinateur a retiré le bâton " + (randomPosition + 1));
      Tools.sleep(1000);
    } else {
      for (int i = 0; i < this.batonsList.size(); i++) {
        if (this.batonsList.get(i)) {
          this.batonsList.set(i, false);
          this.lastBatonsTaken[index] = i + 1;
          System.out.println("L'ordinateur a retiré le dernier bâton " + (i + 1));
          break;
        }
      }
    }
  }

  public void resetLastBatonsTaken() {
    this.lastBatonsTaken[0] = 0;
    this.lastBatonsTaken[1] = 0;
    this.lastBatonsTaken[2] = 0;
  }

  public int getBatons() {
    return (int) this.batonsList.stream().filter(Boolean::booleanValue).count();
  }
}
