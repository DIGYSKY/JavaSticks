package src;

import src.game.GameBaton;

abstract class Baton {
  public static void main(String[] args) {
    System.out.println("Bienvenue sur le jeu du baton !");

    startGame();
  }

  public static void startGame() {
    new GameBaton();
  }
}
