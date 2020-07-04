package zork_game.items.potions;

import zork_game.Player;
import zork_game.items.Item;

public abstract class Potion extends Item {

    @Override
    protected String ability() {
        return "Increase player's hp by " + getPowerVolume(); // increaseHp;
    }

    @Override
    public void initialize(String name, int powerVolume) {
        super.initialize(name,powerVolume);
    }

    @Override
    public void use(Player player) {
        System.out.println("Your HP increase by " + getPowerVolume());
    }
}
