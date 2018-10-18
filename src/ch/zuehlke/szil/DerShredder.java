package ch.zuehlke.szil;

import java.awt.Color;
import robocode.AdvancedRobot;

public class DerShredder extends AdvancedRobot {

  public static final Color SHREDDER_PURPLE = new Color(102, 70, 127);
  public static final Color SHREDDER_GREY = new Color(156, 162, 197);

  @Override
  public void run() {
    defineShredderAppearance();

    int turnCounter = 0;

    double battleFieldHeight = getBattleFieldHeight();
    double battleFieldWidth = getBattleFieldWidth();
    int sentryBorderSize = getSentryBorderSize();
    new BattleField(battleFieldHeight, battleFieldWidth, sentryBorderSize);
    while (true) {

      double x = getX();
      double y = getY();

      turnGunRight(2);
      //setFire(5);



      if (turnCounter % 20 < 10) {
        setAhead(40000);
      } else {
        setBack(40000);
      }

      turnCounter++;
      execute();
    }
  }


  private void defineShredderAppearance() {
    setBodyColor(SHREDDER_PURPLE);
    setGunColor(SHREDDER_GREY);
    setRadarColor(Color.black);
    setScanColor(Color.yellow);
  }
}
