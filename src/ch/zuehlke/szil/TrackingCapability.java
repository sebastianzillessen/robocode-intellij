package ch.zuehlke.szil;

import ch.zuehlke.szil.trackingrobot.RoboState;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class TrackingCapability {
    private TrackingRobot trackingRobot;
    private HashMap<String, RoboState> roboStateHashMap = new HashMap<>();

    public HashMap<String, RoboState> getRoboStateHashMap() {
        return roboStateHashMap;
    }

    public void setRobot(TrackingRobot trackingRobot) {
        this.trackingRobot = trackingRobot;
    }

    public void logRobotSeenEvent(ScannedRobotEvent event) {
        RoboState roboState = roboStateHashMap.getOrDefault(event.getName(), new RoboState(this.trackingRobot));
        roboState.seen(event);
        roboStateHashMap.put(event.getName(), roboState);
    }


    Optional<RoboState> getClosestRobot() {
        return getRoboStateHashMap().values().stream()
                .filter(r-> !r.isDead())
                .sorted(Comparator.comparingInt(RoboState::getDistance))
                .findFirst();
    }

    public void init() {

    }

    public void logRobotDeathEvent(RobotDeathEvent event) {
        RoboState roboState = roboStateHashMap.getOrDefault(event.getName(), new RoboState(this.trackingRobot));
        roboState.die();
        roboStateHashMap.put(event.getName(), roboState);
    }
}
