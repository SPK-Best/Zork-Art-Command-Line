package zork_game.command;

import zork_game.Game;
import zork_game.items.Item;
import zork_game.items.potions.Potion;
import zork_game.items.weapons.Weapon;

public class UseCommand extends Command {

    private Game game;

    public UseCommand(Game game){
        super("Use an item that player carries","use weapon / use item", 1);
        this.game = game;
    }

    @Override
    public void apply() {
        if(game.player.isCarryItem() && getParameter().equals("item")) {
            Item item = game.player.dropItem();
            System.out.printf("You use %s\n", item.getItemName());
            item.use(game.player);
        }
        else if (game.player.isCarryWeapon() && getParameter().equals("weapon")){
            Weapon weapon = game.player.getWeapon();
            weapon.use(game.player);
        }
        else {
            System.out.format("You carry nothing.\n");
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
