package ch.zuehlke.szil.trackingrobot;

import ch.zuehlke.szil.TrackingRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;
import java.util.ArrayList;

public class RoboState {
    private TrackingRobot trackingRobot;
    private ArrayList<ScannedRobotEvent> events = new ArrayList<>();
    private int lastScannedX;
    private int lastScannedY;

    public RoboState(TrackingRobot trackingRobot, Color color) {
        this.trackingRobot = trackingRobot;
    }

    public RoboState(TrackingRobot trackingRobot) {
        this.trackingRobot = trackingRobot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RoboState roboState = (RoboState) o;

        return this.events.equals(roboState.events);
    }

    @Override
    public int hashCode() {
        return this.events.hashCode();
    }

    public void seen(ScannedRobotEvent event) {
        this.events.add(event);

        double angle = Math.toRadians((trackingRobot.getHeading() + event.getBearing()) % 360);

        // Calculate the coordinates of the robot
        lastScannedX = (int) (trackingRobot.getX() + Math.sin(angle) * event.getDistance());
        lastScannedY = (int) (trackingRobot.getY() + Math.cos(angle) * event.getDistance());

        System.out.println(" Robot updated: " + this);
    }

    public ScannedRobotEvent getLatestState() {
        return this.events.get(this.events.size() - 1);
    }

    public int getLastScannedX() {
        return lastScannedX;
    }

    public int getLastScannedY() {
        return lastScannedY;
    }

    @Override
    public String toString() {
        return "RoboState{" +
                "  lastScannedX=" + lastScannedX +
                ", lastScannedY=" + lastScannedY +
                '}';
    }

    public int getDistance() {
        return (int) getLatestState().getDistance();
    }
}
