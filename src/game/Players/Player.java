package src.game.Players;

import src.game.GameBaton;
import src.utils.Scan;

public class Player {
  private String name;
  private int score = 0;
  private boolean isComputer = false;
  private GameBaton game;
  private int level = 0;
  private int takenBaton = 0;
  private Computer computer;

  public Player(String name) {
    this.name = name;
  }

  public Player() {
    this.name = "Ordinateur";
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

  public void gamePlay() {
    this.takenBaton = 0;
    if (this.isComputer) {
      this.computer.play(this.level);
    } else {
      this.humanPlay();
    }
  }

  private void humanPlay() {
    System.out.println("Votre tour... Quels batons voulez-vous retirer ? \nNoter les positions des batons à retirer (ex : 1 2 3)");
    String batons = Scan.scanString();

    if (batons.split(" ").length < 1 || batons.split(" ").length > 3) {
      System.out.println("Vous devez retirer entre 1 et 3 batons.");
      this.humanPlay();
    } else if ((batons.split(" ").length + this.takenBaton) <= 3) {
      this.game.resetLastBatonsTaken();
      this.takenBaton = batons.split(" ").length;
      for (String baton : batons.split(" ")) {
        if (!this.game.takeBaton(Integer.parseInt(baton))) {
          System.out.println("Vous ne pouvez pas retirer ce baton. " + baton);
          this.humanPlay();
          return;
        }
      }
    } else {
      System.out.println("Vous ne pouvez retirer que 3 batons maximum.");
      this.humanPlay();
    }
  }

  public void setGame(GameBaton game) {
    this.game = game;
    this.computer = new Computer(game);
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return this.level;
  }

  public boolean levelUp() {
    if (!this.isComputer) {
      if (this.score >= 50 && this.level < 1) {
        this.level++;
        return true;
      } else if (this.score >= 150 && this.level < 2) {
        this.level++;
        return true;
      } else if (this.score >= 300 && this.level < 3) {
        this.level++;
        return true;
      }
    } else {
      this.level++;
      return true;
    }
    return false;
  }

  public void scoreUp() {
    this.score += 50;
  }

  public boolean isComputer() {
    return this.isComputer;
  }

  public void setIsComputer(boolean isComputer) {
    this.isComputer = isComputer;
  }
}
