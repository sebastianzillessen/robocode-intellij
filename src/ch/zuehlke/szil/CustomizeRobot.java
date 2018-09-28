package ch.zuehlke.szil;

import java.awt.*;

public class CustomizeRobot implements Capability {
    private TrackingRobot trackingRobot;

    public CustomizeRobot(TrackingRobot trackingRobot) {

        this.trackingRobot = trackingRobot;
    }

    @Override
    public void onInit() {
        trackingRobot.setBodyColor(new Color(128, 128, 50));
        trackingRobot.setGunColor(new Color(50, 50, 20));
        trackingRobot.setRadarColor(new Color(200, 200, 70));
        trackingRobot.setScanColor(Color.white);
        trackingRobot.setBulletColor(Color.blue);
    }

    @Override
    public void onStep() {

    }
}
