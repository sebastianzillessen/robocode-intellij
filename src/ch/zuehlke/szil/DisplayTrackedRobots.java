package ch.zuehlke.szil;

import ch.zuehlke.szil.trackingrobot.RoboState;
import robocode.AdvancedRobot;

import java.awt.*;
import java.util.Collection;
import java.util.Optional;

public class DisplayTrackedRobots {
    public static final int CIRCLE_SIZE = 60;
    public static final int ROBOT_SQUARE_SIZE = 40;
    static final Color[] ROBO_COLORS = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY};
    private TrackingCapability trackingCapability;
    private AdvancedRobot robot;

    public DisplayTrackedRobots(TrackingCapability trackingCapability, AdvancedRobot robot) {

        this.trackingCapability = trackingCapability;
        this.robot = robot;
    }

    public void draw(Graphics2D g) {
        Collection<RoboState> robotStates = this.trackingCapability.getRoboStateHashMap().values();

        drawLastKnownPositions(g, robotStates);
        drawClosestRobot(g);
    }

    private void drawClosestRobot(Graphics2D g) {
        Optional<RoboState> closestRobot = trackingCapability.getClosestRobot();
        closestRobot.ifPresent(roboState -> drawClosestRobot(g, roboState));
    }


    public void drawLastKnownPositions(Graphics2D g, Collection<RoboState> robotStates) {

        for (RoboState s : robotStates) {
            if (s.isDead()) {
                g.setColor(Color.GRAY);
            } else {
                g.setColor(Color.YELLOW);
            }

            int lastScannedX = s.getLastScannedX();
            int lastScannedY = s.getLastScannedY();

            // Draw a filled square on top of the scanned robot that covers it
            g.fillRect(lastScannedX - ROBOT_SQUARE_SIZE / 2, lastScannedY - ROBOT_SQUARE_SIZE / 2, ROBOT_SQUARE_SIZE, ROBOT_SQUARE_SIZE);

        }


    }

    private void drawClosestRobot(Graphics2D g, RoboState roboState) {
        g.setColor(Color.RED);

        int lastScannedX = roboState.getLastScannedX();
        int lastScannedY = roboState.getLastScannedY();
        // Draw taget line.
        g.drawLine(lastScannedX, lastScannedY, (int) robot.getX(), (int) robot.getY());

        g.drawOval(lastScannedX - DisplayTrackedRobots.CIRCLE_SIZE / 2, lastScannedY - DisplayTrackedRobots.CIRCLE_SIZE / 2, DisplayTrackedRobots.CIRCLE_SIZE, DisplayTrackedRobots.CIRCLE_SIZE);
    }

}
