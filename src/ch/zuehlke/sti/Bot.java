package ch.zuehlke.sti;

import javafx.geometry.Pos;

public class Bot {

    private final String name;
    private Position position;

    public Bot(String name, Position position){
        this.name = name;
        this.position = position;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }

    

}
