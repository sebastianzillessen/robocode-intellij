package ch.zuehlke.szil;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class TrackingRobot extends AdvancedRobot {
    TrackingCapability trackingCapability = new TrackingCapability();
    DisplayTrackedRobots displayTrackedRobots = new DisplayTrackedRobots(trackingCapability, this);


    public TrackingRobot() {
        trackingCapability.setRobot(this);

    }

    public void run() {
        setTurnGunRight(99999);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        System.out.println(e);
        trackingCapability.logRobotSeenEvent(e);
    }

    @Override
    public void onPaint(Graphics2D g) {
        displayTrackedRobots.draw(g);
    }
}
