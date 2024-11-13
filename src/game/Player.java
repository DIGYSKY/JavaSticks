package src.game;

import src.utils.Scan;
import src.utils.Tools;

public class Player {
  private String name;
  private int score = 0;
  private boolean isComputer = false;
  private static int nbPlayers = 0;
  private GameBaton game;
  private int level = 0;

  public Player(String name) {
    this.name = name;
    nbPlayers++;
  }

  public Player() {
    nbPlayers++;
    this.name = "Ordinateur " + nbPlayers;
    this.isComputer = true;
  }

  public String getName() {
    return this.name;
  }

  public int getScore() {
    return this.score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public static int getNbPlayers() {
    return nbPlayers;
  }

  public void gamePlay() {
    if (this.isComputer) {
      this.computerPlay(this.level);
    } else {
      this.humanPlay();
    }
  }

  private void computerPlay(int level) {
    System.out.println("L'ordinateur joue...");
    if (level == 0) {
      this.game.setBatons(this.game.getBatons() - 1);
    } else if (level == 1) {
      this.game.setBatons(this.game.getBatons() - 2);
    } else {
      this.game.setBatons(this.game.getBatons() - 3);
    }

    Tools.sleep(1000);
  }

  private void humanPlay() {
    System.out.println("Votre tour... Combien de batons voulez-vous retirer ?");
    int batons = Scan.scanIntNumber();

    if (batons < 1 || batons > 3) {
      System.out.println("Vous devez retirer entre 1 et 3 batons.");
      this.humanPlay();
    } else {
      this.game.setBatons(this.game.getBatons() - batons);
    }
  }

  public void setGame(GameBaton game) {
    this.game = game;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return this.level;
  }

  public boolean isComputer() {
    return this.isComputer;
  }
}
