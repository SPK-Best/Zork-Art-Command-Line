package zork_game.Rooms;

import zork_game.items.Item;

public abstract class RoomFactory {

    public static RoomFactory northRoom;
    public RoomFactory southRoom;
    public RoomFactory westRoom;
    public RoomFactory eastRoom;

    public Item item;

    public RoomFactory (){
        northRoom = null;
        southRoom = null;
        westRoom = null;
        eastRoom = null;

        this.item = null;
    }
}
