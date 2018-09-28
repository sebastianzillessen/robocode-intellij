package ch.zuehlke.szil;

import robocode.AdvancedRobot;

public class SpinningMovement implements Capability {
    private AdvancedRobot robot;

    public SpinningMovement(AdvancedRobot robot) {
        this.robot = robot;
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onStep() {
        // Tell the game that when we take move,
        // we'll also want to turn right... a lot.
        robot.setTurnRight(100);
        // Limit our speed to 5
        robot.setMaxVelocity(5);
        // Start moving (and turning)
        robot.ahead(100);
        // Repeat.
    }

}
