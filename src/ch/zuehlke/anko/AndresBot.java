/**
 * Copyright (c) 2001-2018 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 */
package ch.zuehlke.anko;


import ch.zuehlke.helpers.Helper;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AndresBot extends AdvancedRobot {

    int turnDirection = 1;

    List<Color> colors = new ArrayList<Color>();
    
    public void run() {
        // Set colors
        colors.add(Color.blue);
        colors.add(Color.orange);
        colors.add(Color.white);
        colors.add(Color.cyan);
        colors.add(Color.black);

        setNewColors();
        setRadarColor(Color.black);
        setScanColor(Color.yellow);

        // Loop forever
        while (true) {
            
            setNewColors();
            moveInSafeZoneIfNeeded();
            goToRandomLocation();
        }
    }

    private void goToRandomLocation() {
        int destinationX = rand.nextInt() % ((int)getBattleFieldWidth() - getSentryBorderSize() * 2) + (int)getSentryBorderSize();
        int destinationY = rand.nextInt() % ((int)getBattleFieldHeight() - getSentryBorderSize() * 2) + (int)getSentryBorderSize();
        goTo(destinationX, destinationY);
    }

    private void moveInSafeZoneIfNeeded() {
        double gotoX;
        double gotoY;
        if (getX() < getSentryBorderSize()) {
            gotoX = getSentryBorderSize() + 10;
            gotoY = getY();
        } else if (getBattleFieldWidth() - getSentryBorderSize() < getX()) {
            gotoX = getSentryBorderSize() + 10;
            gotoY = getY();
        } else {
            gotoX = getX();
            gotoY = getY();
        }

        if (getY() < getSentryBorderSize()) {
            gotoY = getSentryBorderSize() + 10;
        } else if (getBattleFieldHeight() - getSentryBorderSize() < getY()) {
            gotoY = getSentryBorderSize() + 10;
        } else {
            gotoY = getY();
        }

        if (gotoX != getX() || gotoY != getY()) {
            goTo(gotoX, gotoY);
        }
    }

      //Move towards an x and y coordinate

    void goTo(double x, double y) {
        double dist = 20;
        double angle = Math.toDegrees(Helper.absbearing(getX(), getY(), x, y));
        double r = turnTo(angle);
        setAhead(dist * r);
    }


    //Turns the shortest angle possible to come to a heading, then returns the direction the
     //the bot needs to move in.

    int turnTo(double angle) {
        double ang;
        int dir;
        ang = Helper.normaliseBearing(getHeading() - angle);
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

    private static Random rand = new Random();
    private void setNewColors() {
        
        int colorid = rand.nextInt() % colors.size();
        setBodyColor(colors.get(colorid));
        colorid = rand.nextInt() % colors.size();
        setGunColor(colors.get(colorid));
        colorid = rand.nextInt() % colors.size();
        setBulletColor(colors.get(colorid));

    }

    /**
     * onScannedRobot: Fire hard!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        double absoluteBearing = this.getHeading() + e.getBearing();
        double bearingFromGun = Utils.normalRelativeAngleDegrees(absoluteBearing - this.getGunHeading());
        if (Math.abs(bearingFromGun) <= 3.0D) {
            this.turnGunRight(bearingFromGun);
            if (this.getGunHeat() == 0.0D) {
                this.fire(Math.min(3.0D - Math.abs(bearingFromGun), this.getEnergy() - 0.1D));
            }
        } else {
            this.turnGunRight(bearingFromGun);
        }

        if (bearingFromGun == 0.0D) {
            this.scan();
        }
    }

    /**
     * onHitRobot:  If it's our fault, we'll stop turning and moving,
     * so we need to turn again to keep spinning.
     */
    public void onHitRobot(HitRobotEvent e) {
        if (e.getBearing() >= 0.0D) {
            this.turnDirection = 1;
        } else {
            this.turnDirection = -1;
        }

        this.turnRight(e.getBearing());
        if (e.getEnergy() > 16.0D) {
            this.fire(3.0D);
        } else if (e.getEnergy() > 10.0D) {
            this.fire(2.0D);
        } else if (e.getEnergy() > 4.0D) {
            this.fire(1.0D);
        } else if (e.getEnergy() > 2.0D) {
            this.fire(0.5D);
        } else if (e.getEnergy() > 0.4D) {
            this.fire(0.1D);
        }

        this.ahead(40.0D);
    }
}
