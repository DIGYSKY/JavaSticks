package src;
// Scan => Scan.java
// Players => Players.java

import src.game.Players;
import src.utils.Scan;

public class Baton {
  public static void main(String[] args) {
    System.out.println("Bienvenue sur le jeu du baton !");
    game();
  }

  public static void game() {
    System.out.println("Le jeu du baton est un jeu de logique qui consiste retirer des batons de la table.\n Le but du jeu est de ne pas retirer le dernier baton de la table.\n");
    System.out.println("Jouez-vous seul ou avec un autre joueur ?");
    boolean isSolo = Scan.scanBoolean();
    if (isSolo) {
      System.out.println("Entrez votre nom : ");
      Players player = new Players();
      player.player(Scan.scanString());
    } else {
      System.out.println("Entrez le nom du premier joueur : ");
      Players player1 = new Players();
      player1.player(Scan.scanString());
      System.out.println("Entrez le nom du deuxième joueur : ");
      Players player2 = new Players();
      player2.player(Scan.scanString());
    }
  }
}
