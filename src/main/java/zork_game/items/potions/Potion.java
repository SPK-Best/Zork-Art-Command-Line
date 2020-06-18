package zork_game.items.potions;

import zork_game.items.Item;

public class Potion extends Item {
    public int increaseHp;

    public Potion(String name, int increaseHp){
        this.itemName = name;
        this.increaseHp = increaseHp;
        this.ability = "Increase player's hp by " + increaseHp;
    }
}
