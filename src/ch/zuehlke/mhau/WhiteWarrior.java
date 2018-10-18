package ch.zuehlke.mhau;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ch.zuehlke.szil.Helper.*;
import static java.util.Comparator.comparing;

public class WhiteWarrior extends AdvancedRobot {

    Map<String, ScannedRobot> scannedRobots = new HashMap<>();

    @Override
    public void run() {
        setBodyColor(Color.black);
        setGunColor(Color.white);
        setRadarColor(Color.red);
        setScanColor(Color.yellow);

        double battleFieldHeight = getBattleFieldHeight();
        double battleFieldWidth = getBattleFieldWidth();
        int sentryBorderSize = getSentryBorderSize();

        while (true) {
            Optional<ScannedRobot> optionalWeakest = scannedRobots.values().stream()
                    .min(comparing(ScannedRobot::getEnergy));

            goTo(battleFieldWidth / 2, battleFieldHeight / 2);

            fire(5);

            turnLeft(5);
            turnRadarLeft(5);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        String name = event.getName();
        double bearing = normaliseBearing(event.getBearingRadians());
        double distance = event.getDistance();
        double energy = event.getEnergy();
        double heading = normaliseHeading(event.getHeadingRadians());
        double velocity = event.getVelocity();

        scannedRobots.put(name, new ScannedRobot(bearing, distance, energy, heading, velocity));
    }

    void goTo(double x, double y) {
        double dist = 20;
        double angle = Math.toDegrees(absbearing(getX(), getY(), x, y));
        double r = turnTo(angle);
        setAhead(dist * r);
    }

    //Turns the shortest angle possible to come to a heading, then returns the direction the
    //the bot needs to move in.
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
