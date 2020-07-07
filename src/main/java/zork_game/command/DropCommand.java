package zork_game.command;

import zork_game.Game;
import zork_game.items.Item;
import zork_game.items.weapons.Weapon;

public class DropCommand extends Command {

    private Game game;

    public DropCommand(Game game){
        super("Drop an item that player carries","drop weapon / drop item", 1);
        this.game = game;
    }

    @Override
    public void apply() {
        if (getParameter().equals("item")){            // Case : Player wants to drop item
            if(game.player.isCarryItem()) {
                Item item = game.player.dropItem();
                System.out.format("You drop %s\n", item.getItemName());
            }
            else {
                System.out.println("You carry no item");
            }
        }
        else if (getParameter().equals("weapon")){      // Case : Player wants to drop weapon
            if(game.player.isCarryWeapon()) {
                Weapon weapon = game.player.dropWeapon();
                System.out.format("You drop %s\n", weapon.getItemName());
            } else {
                System.out.println("You carry no weapon");
            }
        }
        else {          // Case : Player does not type item or weapon
            System.out.println("Please specific weapon or item");
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
