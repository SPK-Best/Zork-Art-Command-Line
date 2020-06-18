package zork_game.items.weapons;

import zork_game.items.Item;

public class Weapons extends Item {
    int attackPower;

    public Weapons(String name, int attackPower){
        this.itemName = name;
        this.attackPower = attackPower;
        this.ability = "Increase player's attack power by " + attackPower;
    }
}
