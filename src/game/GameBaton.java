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
  private int currentPlayer = 1;
  private int batonsDefault = 20;
  private ArrayList<Boolean> batonsList = new ArrayList<>();
  private String version = "1.0.0 Genesis";

  public GameBaton() {
    String[] playerData = this.loadSave();
    if (playerData != null) {
      System.out.println("Sauvegarde trouvée !");
      System.out.println("Voulez-vous charger la sauvegarde ? (o/n)");
      String input = Scan.scanString().toLowerCase();
      if (input.equals("o")) {
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
    System.out.println("Le jeu du baton est un jeu de logique qui consiste retirer des batons de la table.\n Le but du jeu est de ne pas retirer le dernier baton de la table.\n");
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

    System.out.println("        Menu du jeu du baton " + this.version);
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

    String input = Scan.scanString();

    if (input.equals("1")) {
      this.initGame();
    } else if (input.equals("2")) {
      System.out.println("Au revoir !");
      System.exit(0);
    } else {
      this.mainMenu();
    }
  }
  
  private void gameLoop() {
    while (true) {
      if (this.batonsList.stream().filter(Boolean::booleanValue).count() <= 0) break;

      Tools.clearConsole();
      System.out.println("        Jeu du baton " + this.version);
      System.out.println();
      System.out.println("Tour du joueur " + (this.currentPlayer == 1 ? this.player1.getName() : this.player2.getName()));
      System.out.println();
      System.out.println("Score : " + this.player1.getScore() + " - " + this.player2.getScore());
      System.out.println("Niveaux : " + this.player1.getLevel() + " - " + this.player2.getLevel());
      System.out.println();

      String batonsDisplay = "";
      String batonsPosition = "";

      for (int i = 0; i < this.batonsList.size(); i++) {
        batonsDisplay += this.batonsList.get(i) ? "|   " : (i > 8 ? "    " : "    ");
        batonsPosition += (i + 1);

        if (i >= 8) {
          batonsPosition += "  ";
        } else {
          batonsPosition += "   ";
        }
      }

      System.out.println(batonsDisplay);
      System.out.println(batonsPosition);
      System.out.println();

      if (this.currentPlayer == 1) {
        this.player1.gamePlay();
      } else {
        System.out.println("Tour du joueur " + this.player2.getName());
        this.player2.gamePlay();
      }

      this.currentPlayer = (this.currentPlayer == 1) ? 2 : 1;
    }

    this.endGame();
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

    System.out.println("Les scores finaux sont : " + this.player1.getName() + " : " + this.player1.getScore() + " et " + this.player2.getName() + " : " + this.player2.getScore());
    Tools.sleepKeyboard();
    Tools.clearConsole();

    this.mainMenu();
  }

  public boolean takeBaton(int position) {
    try {
      this.batonsList.set(position - 1, false);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public void takeBatonComputer() {
    int randomPosition = (int) (Math.random() * this.batonsList.size());
    if (this.batonsList.get(randomPosition)) {
      this.batonsList.set(randomPosition, false);
      System.out.println("L'ordinateur a retiré le baton " + (randomPosition + 1));
    } else {
      this.takeBatonComputer();
    }
  }

  public int getBatons() {
    return (int) this.batonsList.stream().filter(Boolean::booleanValue).count();
  }
}
