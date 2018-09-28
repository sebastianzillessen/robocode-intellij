package ch.zuehlke.szil;

import robocode.ScannedRobotEvent;

public class ShootingCapability {
    private TrackingRobot trackingRobot;

    public ShootingCapability(TrackingRobot trackingRobot) {

        this.trackingRobot = trackingRobot;
    }

    public void init() {

    }

    public void shoot(ScannedRobotEvent e) {
        trackingRobot.fire(1);
    }
}
