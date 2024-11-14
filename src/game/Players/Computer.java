package src.game.Players;

import src.game.GameBaton;
import src.utils.Tools;

public class Computer {
  private GameBaton game;

  public Computer(GameBaton game) {
    this.game = game;
    System.out.println("L'ordinateur est prêt");
  }

  public void play(int level) {
    switch (level) {
      case 0:
        this.level0();
        break;
      case 1:
        this.level1();
        break;
      case 2:
        this.level2();
        break;
      case 3:
        this.level3();
        break;
      default:
        this.level3();
        break;
    }

    Tools.sleep(1500);
  }

  private void level0() {
    int random = ((int) (Math.random() * 3) + 1) % this.game.getBatons();
    this.takeBatons(random);
  }

  private void level1() {
    if (this.game.getBatons() > 3) {
      int random = ((int) (Math.random() * 3) + 1) % this.game.getBatons();
      this.takeBatons(random);
    } else if (this.game.getBatons() > 2) {
      this.takeBatons(2);
    } else if (this.game.getBatons() > 1) {
      this.takeBatons(1);
    } else {
      this.game.takeBatonComputer();
    }
  }

  private void level2() {
    if (this.game.getBatons() > 4) {
      int random = ((int) (Math.random() * 3) + 1) % this.game.getBatons();
      this.takeBatons(random);
    } else if (this.game.getBatons() > 3) {
      this.takeBatons(3);
    } else if (this.game.getBatons() > 2) {
      this.takeBatons(2);
    } else if (this.game.getBatons() > 1) {
      this.takeBatons(1);
    } else {
      this.game.takeBatonComputer();
    }
  }

  private void level3() {
    int remainingBatons = this.game.getBatons();
    int batonsToTake;

    if (remainingBatons % 4 == 0) {
      batonsToTake = 3;
    } else if (remainingBatons % 4 == 3) {
      batonsToTake = 2;
    } else if (remainingBatons % 4 == 2) {
      batonsToTake = 1;
    } else {
      batonsToTake = 1 + (int) (Math.random() * 3);
    }

    this.takeBatons(batonsToTake);
  }

  private void takeBatons(int batons) {
    for (int i = 0; i < batons; i++) {
      this.game.takeBatonComputer();
    }
  }
}
