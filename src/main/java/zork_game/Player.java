package zork_game;

import zork_game.items.Item;

public class Player {
    public boolean alive;
    public int hp;
    public static final int MAX_HP = 100;
    public int attackPower;
    public Item item;

    public Player(){
        alive = true;
        hp = MAX_HP;
        attackPower = 10;
        item = null;
    }
}
