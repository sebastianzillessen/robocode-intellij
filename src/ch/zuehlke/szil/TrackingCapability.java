package ch.zuehlke.szil;

import ch.zuehlke.szil.trackingrobot.RoboState;
import robocode.ScannedRobotEvent;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class TrackingCapability {
    private TrackingRobot trackingRobot;

    public HashMap<String, RoboState> getRoboStateHashMap() {
        return roboStateHashMap;
    }

    private HashMap<String, RoboState> roboStateHashMap = new HashMap<>();


    public void setRobot(TrackingRobot trackingRobot) {
        this.trackingRobot = trackingRobot;
    }

    public void logRobotSeenEvent(ScannedRobotEvent event) {
        RoboState roboState = roboStateHashMap.getOrDefault(event.getName(), new RoboState(this.trackingRobot));
        roboState.seen(event);
        roboStateHashMap.put(event.getName(), roboState);
    }


    Optional<RoboState> getClosestRobot(Collection<RoboState> values) {
        return values.stream().sorted(Comparator.comparingInt(RoboState::getDistance))
                .findFirst();
    }
}
