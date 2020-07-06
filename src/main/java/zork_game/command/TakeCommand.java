package zork_game.command;

import zork_game.Game;
import zork_game.Room;
import zork_game.items.Item;
import zork_game.items.weapons.Weapon;

public class TakeCommand extends Command {

    private Game game;

    public TakeCommand(Game game) {
        super("Picks up an item in the current room", "take", 0);
        this.game = game;
    }

    public void apply() {
        Room curRoom = game.map.getRoom(game.player.getPosX(), game.player.getPosY());
        if (curRoom.isItemExists()) {
            Item item = curRoom.getItem();
            if(item instanceof Weapon) {
                if(!game.player.isCarryWeapon()) {
                    System.out.format("You take %s\n", item.getItemName());
                    game.player.carryWeapon((Weapon) item);
                    curRoom.removeItem();
                } else {
                    System.out.println("Cannot take it, drop your weapon first.");
                }
            }
            else {
                if(!game.player.isCarryItem()) {
                    System.out.format("You take %s\n", item.getItemName());
                    game.player.carryItem(item);
                    curRoom.removeItem();
                }
                else {
                    System.out.println("Cannot take it, drop your item first.");
                }
            }
        } else {
            System.out.println("No items in the room.");
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
