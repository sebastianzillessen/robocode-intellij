package ch.zuehlke.szil;

public class BattleField {

  private final double battleFieldHeight;
  private final double battleFieldWidth;
  private final int sentryBorderSize;

  public BattleField(double battleFieldHeight, double battleFieldWidth, int sentryBorderSize) {
    this.battleFieldHeight = battleFieldHeight;
    this.battleFieldWidth = battleFieldWidth;
    this.sentryBorderSize = sentryBorderSize;


  }

  public boolean isInBattlefield(double x, double y) {
    return x > sentryBorderSize && x < battleFieldWidth - sentryBorderSize && y > sentryBorderSize && y < battleFieldHeight - sentryBorderSize;
  }


}
