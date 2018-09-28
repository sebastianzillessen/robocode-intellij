package ch.zuehlke.szil;

public class ContinousRadarMovement {
    private TrackingRobot trackingRobot;

    public ContinousRadarMovement(TrackingRobot trackingRobot) {

        this.trackingRobot = trackingRobot;
    }

    public void init(){
        trackingRobot.setTurnGunRight(99999);
    }

    public void run() {

    }
}
