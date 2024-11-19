package src.game.Players;

import src.game.GameBaton;
import src.utils.Scan;

public class Player {
  private GameBaton game;
  private Computer computer;
  private String name;
  private boolean isComputer = false;
  private int score = 0;
  private int level = 0;
  private int position = 1;
  private int takenBaton = 0;

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
    if (this.isComputer) {
      this.computer.play(this.level);
    } else {
      this.humanPlayLoop();
    }
  }

  private void humanPlayLoop() {
    this.takenBaton = 0;
    while (this.takenBaton < 3) {
      char key = Scan.scanKey();
      if (key == 'q') {
        this.setPosition(this.getPosition() - 1);
        this.game.displayGame(this.getPosition());
      } else if (key == 'd') {
        this.setPosition(this.getPosition() + 1);
        this.game.displayGame(this.getPosition());
      } else if (key == 'z') {
        if (this.game.takeBaton(this.getPosition())) {
          this.takenBaton++;
          this.game.displayGame(this.getPosition());
        }
      } else if (key == '\n') {
        if (this.takenBaton > 0) {
          break;
        }
      } else if (key == '\u001B') {
        this.game.menuInGame();
      }
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

  public void setPosition(int position) {
    this.position = position;
    if (this.position < 1) {
      this.position = this.game.batonsDefault;
    } else if (this.position > this.game.batonsDefault) {
      this.position = 1;
    }
  }

  public int getPosition() {
    return this.position;
  }
}
