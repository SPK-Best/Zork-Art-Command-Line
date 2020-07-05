package zork_game;

import zork_game.items.Item;
import zork_game.monsters.Monster;

public class Room {

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private Item item;
    private Monster monster;
    // Wall status in North, East, South, West direction
    boolean[] walls = {false,false,false,false}; // true:wall, false:no wall

    public Room(boolean wallN, boolean wallE, boolean wallS, boolean wallW) {
        this.walls = new boolean[] {wallN, wallE, wallS, wallW};
        this.monster = null;
        this.item = null;
    }

    public void addMonster(Monster monster) {
        this.monster = monster;
    }

    public boolean isMonsterExist() {
        return this.monster != null;
    }

    public void removeMonster() {
        this.monster = null;
    }

    public Monster getMonster() {
        return this.monster;
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public boolean isItemExists() {
        return this.item != null;
    }

    public void removeItem() {
        item = null;
    }

    public Item getItem() {
        return item;
    }

    public boolean hasNWall() {
        return this.walls[NORTH];
    }

    public boolean hasEWall() {
        return this.walls[EAST];
    }

    public boolean hasSWall() {
        return this.walls[SOUTH];
    }

    public boolean hasWWall() {
        return this.walls[WEST];
    }
}
