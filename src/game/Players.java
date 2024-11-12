package src.game;

public class Players {
  private String name;
  private int score;
  private static int nbPlayers = 0;

  public void player(String name) {
    this.name = name;
    this.score = 0;
    nbPlayers++;
  }

  public String getName() {
    return this.name;
  }

  public int getScore() {
    return this.score;
  }

  public static int getNbPlayers() {
    return nbPlayers;
  }
}
