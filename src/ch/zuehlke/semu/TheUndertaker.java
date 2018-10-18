package ch.zuehlke.semu;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;

public class TheUndertaker extends AdvancedRobot {

    private String target;

    public void run() {
        setBodyColor(Color.BLACK);
        setRadarColor(Color.RED);
        setBulletColor(Color.GREEN);
        setScanColor(Color.WHITE);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            turnRadarRightRadians(Double.POSITIVE_INFINITY);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        if (target == null && !event.isSentryRobot()) {
            target = event.getName();
        }

        if (target.equals(event.getName())) {
            double enemyPosition = event.getBearingRadians() + getHeadingRadians();
            setTurnRadarRightRadians((Utils.normalRelativeAngle(enemyPosition - getRadarHeadingRadians())));

            double toTurn = enemyPosition - getHeadingRadians();
            setTurnGunRightRadians(Utils.normalRelativeAngle((enemyPosition - getGunHeadingRadians() + toTurn)));

            setTurnRightRadians(Utils.normalRelativeAngle(toTurn));
            setAhead(event.getDistance() * 0.5);

            if (event.getDistance() > 300) {
                setFire(1.1);
            } else if (event.getDistance() > 200) {
                setFire(2.0);
            } else {
                setFire(3);
            }
            execute();
        }
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        if (target != null && event.getName().equals(target)) {
            target = null;
        }
    }

}