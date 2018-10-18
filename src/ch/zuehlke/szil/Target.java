package ch.zuehlke.szil;

import robocode.ScannedRobotEvent;

public class Target {
    private ScannedRobotEvent event;

    public Target(ScannedRobotEvent event) {
        this.event = event;
    }

    public boolean isWithinShootingRange() {
        System.out.println("Range: " + event.getDistance());
        return true;
    }

    public boolean isBorderGuard() {
        return "samplesentry.BorderGuard".equals(this.event.getName());
    }
}
