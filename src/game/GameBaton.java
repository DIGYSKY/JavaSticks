package src.game;

import src.utils.Scan;
import src.utils.Tools;

public class GameBaton {
  private Player player1;
  private Player player2;
  private int batons;
  private int level = 0;
  private int currentPlayer = 1;

  public GameBaton() {
    this.setBatons(10);

    this.initGame();
  }

  private void initGame() {
    Tools.clearConsole();

    if (this.player1 != null && this.player2 != null) {
      this.resetGame();
    }

    System.out.println("Le jeu du baton est un jeu de logique qui consiste retirer des batons de la table.\n Le but du jeu est de ne pas retirer le dernier baton de la table.\n");
    System.out.println("Jouez-vous seul ou avec un autre joueur ?");

    String input = Scan.scanString().toLowerCase();
    boolean isSolo = input.contains("seul") || input.contains("solo") || input.contains("oui");

    if (isSolo) {
      System.out.println("Entrez votre nom : ");
      player1 = new Player(Scan.scanString());

      player2 = new Player();

      System.out.println("Les joueurs sont : " + player1.getName() + " et " + player2.getName());
    } else {
      System.out.println("Entrez le nom du premier joueur : ");
      player1 = new Player(Scan.scanString());

      System.out.println("Entrez le nom du deuxième joueur : ");
      player2 = new Player(Scan.scanString());

      System.out.println("Les joueurs sont : " + player1.getName() + " et " + player2.getName());
    }

    this.player1.setGame(this);
    this.player2.setGame(this);

    this.player1.setLevel(this.level);
    this.player2.setLevel(this.level);

    this.gameLoop();
  }

  private void resetGame() {
    this.batons = 10;
    this.currentPlayer = 1;

    this.gameLoop();
  }

  private void mainMenu() {
    Tools.clearConsole();

    System.out.println("        Menu du jeu du baton");
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
    while (this.getBatons() > 1) {
      Tools.clearConsole();
      System.out.println("Il reste " + this.getBatons() + (this.getBatons() <= 1 ? " baton." : " batons."));
      
      if (this.currentPlayer == 1) {
        System.out.println("Tour du joueur " + this.player1.getName());
        this.player1.gamePlay();
      } else {
        System.out.println("Tour du joueur " + this.player2.getName());
        this.player2.gamePlay();
      }
      
      if (this.getBatons() <= 1) {
        break;
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
      this.player1.setScore(this.player1.getScore() + 50);
      System.out.println("Le joueur " + this.player1.getName() + " a gagné !");
      this.player1.setLevel(this.player1.getLevel() + 1);
      if (this.player2.isComputer()) {
        this.player2.setLevel(this.player2.getLevel() + 1);
      }
    } else {
      this.player2.setScore(this.player2.getScore() + 50);
      System.out.println("Le joueur " + this.player2.getName() + " a gagné !");
      if (!this.player2.isComputer()) {
        this.player2.setLevel(this.player2.getLevel() + 1);
      }
    }

    System.out.println("Les scores finaux sont : " + this.player1.getName() + " : " + this.player1.getScore() + " et " + this.player2.getName() + " : " + this.player2.getScore());
    Tools.sleepKeyboard();
    Tools.clearConsole();

    this.mainMenu();
  }

  public void setBatons(int batons) {
    this.batons = batons;
  }

  public int getBatons() {
    return this.batons;
  }
}
