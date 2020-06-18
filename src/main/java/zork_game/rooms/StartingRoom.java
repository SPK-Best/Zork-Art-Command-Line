package zork_game.rooms;

import zork_game.items.Item;
import zork_game.items.weapons.Sword;

public class StartingRoom extends Room {
    public StartingRoom(){
        northRoom = null;
        southRoom = null;
        eastRoom = null;
        westRoom = null;
        item = null;
    }

    public void setNorthRoom(Room northRoom) {
        this.northRoom = new Forest();
    }

    public void setSouthRoom(Room southRoom) {
        this.southRoom = new Forest();
    }

    public void setWestRoom(Room westRoom) {
        this.westRoom = new Forest();
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom = new Castle();
    }

    public void setItem(Item item) {
        this.item = new Sword();
    }
}
