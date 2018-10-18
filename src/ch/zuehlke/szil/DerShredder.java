package ch.zuehlke.szil;

import java.awt.Color;
import java.util.Random;

import robocode.AdvancedRobot;

import static ch.zuehlke.szil.Helper.*;

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
    BattleField battleField = new BattleField(battleFieldHeight, battleFieldWidth, sentryBorderSize);
    while (true) {



      double x = getX();
      double y = getY();
      Random random = new Random();

      turnGunRight(2);
      if (!battleField.isInBattlefield(x, y)) {
        goTo(battleFieldHeight / 2, battleFieldWidth / 2);
      } else {
        if (turnCounter % 20 < 5 + (random.nextInt(10))) {
          setAhead(40000);
        } else {
          setBack(40000);
        }
      }

        setFire(2);
      //setFire(5);



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

  void goTo(double x, double y) {
    double dist = 20;
    double angle = Math.toDegrees(absbearing(getX(), getY(), x, y));
    double r = turnTo(angle);
    setAhead(dist * r);
  }

  int turnTo(double angle) {
    double ang;
    int dir;
    ang = normaliseBearing(getHeading() - angle);
    if (ang > 90) {
      ang -= 180;
      dir = -1;
    } else if (ang < -90) {
      ang += 180;
      dir = -1;
    } else {
      dir = 1;
    }
    setTurnLeft(ang);
    return dir;
  }
}
