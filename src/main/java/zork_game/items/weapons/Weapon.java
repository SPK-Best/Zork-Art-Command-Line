package zork_game.items.weapons;

import zork_game.Player;
import zork_game.items.Item;

public abstract class Weapon extends Item {

    @Override
    protected String ability() {
        return "Increase player's attack power by " + getPowerVolume();//attackPower;
    }

    @Override
    public void initialize(String name, int powerVolume) {
        super.initialize(name, powerVolume);
    }

    @Override
    public void use(Player player) {
    }

}
