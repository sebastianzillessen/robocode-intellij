package ch.zuehlke.szil;

import robocode.*;

import java.awt.*;

public class TrackingRobot extends AdvancedRobot {
    private final ContinousRadarMovement radarMovement;
    private final Capability movementCapability;
    private final CustomizeRobot customization;
    TrackingCapability trackingCapability = new TrackingCapability();
    DisplayTrackedRobots displayTrackedRobots = new DisplayTrackedRobots(trackingCapability, this);

    private boolean running = true;
    private ShootingCapability shootingCapability;


    public TrackingRobot() {
        trackingCapability.setRobot(this);
        radarMovement = new ContinousRadarMovement(this);
        movementCapability = new SpinningMovement(this);
        shootingCapability = new ShootingCapability(this);
        customization = new CustomizeRobot(this);
    }

    public void run() {
        trackingCapability.init();
        radarMovement.init();
        movementCapability.onInit();
        shootingCapability.init();
        customization.onInit();

        while (this.running) {
            movementCapability.onStep();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        trackingCapability.logRobotSeenEvent(e);
        //movementCapability.setTarget(trackingCapability.getClosestRobot());
        shootingCapability.shoot(e);
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        trackingCapability.logRobotDeathEvent(event);
    }

    @Override
    public void onPaint(Graphics2D g) {
        displayTrackedRobots.draw(g);
    }

    @Override
    public void onRoundEnded(RoundEndedEvent event) {
        this.running = false;
    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {

    }
}
