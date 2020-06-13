package zork_game.rooms;

import zork_game.items.Item;
import zork_game.monsters.Monster;

public class Room {

    public Room northRoom = null;
    public Room southRoom = null;
    public Room westRoom = null;
    public Room eastRoom = null;
    public Item item = null;
    public Monster monster = null;

    public void setNorthRoom(Room northRoom) {
        this.northRoom = northRoom;
        northRoom.southRoom = this;
    }

    public void setSouthRoom(Room southRoom) {
        this.southRoom = southRoom;
        southRoom.northRoom = this;
    }

    public void setWestRoom(Room westRoom) {
        this.westRoom = westRoom;
        westRoom.eastRoom = this;
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom = eastRoom;
        eastRoom.westRoom = this;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
