package ch.zuehlke.sti;

import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Battleground {

    public final double battleFieldHeight;
    public final double battleFieldWidth;
    public Position myPosition;
    public HashMap<String, Bot> enemyBots;

    private static Battleground instance;
    public static Battleground getInstance() {
        return instance;
    }

    public Battleground(double battleFieldHeight, double battleFieldWidth) {
        Battleground.instance = this;
        this.battleFieldHeight = battleFieldHeight;
        this.battleFieldWidth = battleFieldWidth;
        this.enemyBots = new HashMap<>();
    }

    public void setMyPosition(Position position) {
        this.myPosition = position;
    }

    public void reportEnemy(String name, Position position) {
        if (enemyBots.containsKey(name)) {
            enemyBots.get(name).updatePosition(position);
        } else {
            enemyBots.put(name, new Bot(name, position));
        }
    }


    public void reportSpottedEnemy(String name, double bearing, double distance) {
        double targetX = (Math.sin(bearing) * distance) + myPosition.x;
        double targetY = (Math.cos(bearing) * distance) + myPosition.y;
        reportEnemy(name, new Position(targetX, targetY));
    }
}
