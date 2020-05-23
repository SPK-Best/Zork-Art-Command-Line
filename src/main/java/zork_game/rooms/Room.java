package zork_game.rooms;

import zork_game.items.Item;

public abstract class Room {

    public Room northRoom;
    public Room southRoom;
    public Room westRoom;
    public Room eastRoom;
    public Item item;
}
