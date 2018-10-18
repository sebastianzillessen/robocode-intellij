package ch.zuehlke.szil;

import static java.lang.Math.PI;

public class Helper {


    /* default movement:
      //Move towards an x and y coordinate

    void goTo(double x, double y) {
        double dist = 20;
        double angle = Math.toDegrees(absbearing(getX(), getY(), x, y));
        double r = turnTo(angle);
        setAhead(dist * r);
    }


    //Turns the shortest angle possible to come to a heading, then returns the direction the
     //the bot needs to move in.

    int turnTo(double angle) {
        double ang;
        int dir;
        ang = normaliseBearing(getHeading() - angle);
        if (ang > 90) {
            ang -= 180;
            dir = -1;
        } else if (ang < -90) {
            ang += 180;
            dir = -1;
        } else {
            dir = 1;
        }
        setTurnLeft(ang);
        return dir;
    }
     */


    //if a bearing is not within the -pi to pi range, alters it to provide the shortest angle
    public static double normaliseBearing(double ang) {
        if (ang > PI)
            ang -= 2 * PI;
        if (ang < -PI)
            ang += 2 * PI;
        return ang;
    }

    //if a heading is not within the 0 to 2pi range, alters it to provide the shortest angle
    public static double normaliseHeading(double ang) {
        if (ang > 2 * PI)
            ang -= 2 * PI;
        if (ang < 0)
            ang += 2 * PI;
        return ang;
    }

    //returns the distance between two x,y coordinates
    public static double getDistance(double x1, double y1, double x2, double y2) {
        double xo = x2 - x1;
        double yo = y2 - y1;
        double h = Math.sqrt(xo * xo + yo * yo);
        return h;
    }

    //gets the absolute bearing between to x,y coordinates
    public static double absbearing(double x1, double y1, double x2, double y2) {
        double xo = x2 - x1;
        double yo = y2 - y1;
        double h = getDistance(x1, y1, x2, y2);
        if (xo > 0 && yo > 0) {
            return Math.asin(xo / h);
        }
        if (xo > 0 && yo < 0) {
            return PI - Math.asin(xo / h);
        }
        if (xo < 0 && yo < 0) {
            return PI + Math.asin(-xo / h);
        }
        if (xo < 0 && yo > 0) {
            return 2.0 * PI - Math.asin(-xo / h);
        }
        return 0;
    }

}
