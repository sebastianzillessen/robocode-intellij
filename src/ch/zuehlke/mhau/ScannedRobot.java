package ch.zuehlke.mhau;

public class ScannedRobot {
    private final double bearing;
    private final double distance;
    private final double energy;
    private final double heading;
    private final double velocity;

    public ScannedRobot(double bearing, double distance, double energy, double heading, double velocity) {
        this.bearing = bearing;
        this.distance = distance;
        this.energy = energy;
        this.heading = heading;
        this.velocity = velocity;
    }

    public double getBearing() {
        return bearing;
    }

    public double getDistance() {
        return distance;
    }

    public double getEnergy() {
        return energy;
    }

    public double getHeading() {
        return heading;
    }

    public double getVelocity() {
        return velocity;
    }
}
