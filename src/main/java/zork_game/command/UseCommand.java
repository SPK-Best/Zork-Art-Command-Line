package zork_game.command;

import zork_game.Game;
import zork_game.items.potions.Potion;

public class UseCommand extends Command {

    public UseCommand(){
        description = "Uses an item that player carries";
    }

    @Override
    public void apply() {
        if (Game.player.item != null && Game.player.item instanceof Potion) {
            Game.player.increaseHp(((Potion) Game.player.item).increaseHp);
            System.out.println("Use " + Game.player.item.itemName);
            Game.player.item = null;
        }
        else {
            System.out.println("No items to use.");
        }
    }
}
