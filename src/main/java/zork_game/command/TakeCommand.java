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
        Room curRoom = game.map.getRoom(game.player.getPosX(), game.player.getPosY());    // Get the position of the current room
        if (curRoom.isItemExists()) {          // Case : There is item exists in this room
            Item item = curRoom.getItem();     // Get the item
            if(item instanceof Weapon) {                // Weapon (Axe, Pistol)
                if(!game.player.isCarryWeapon()) {      // Player does not carry any weapon
                    System.out.format("You take %s\n", item.getItemName());
                    game.player.carryWeapon((Weapon) item);
                    curRoom.removeItem();               // Remove that weapon from the map
                }
                else {                       // Case : Player already carry weapon
                    System.out.println("Cannot take it, drop your weapon first.");
                }
            }
            else {         // Item (Potion)
                if(!game.player.isCarryItem()) {     // Player does not carry any item
                    System.out.format("You take %s\n", item.getItemName());
                    game.player.carryItem(item);
                    curRoom.removeItem();           // Remove that item from the map
                }
                else {          // Case : Player already carry item
                    System.out.println("Cannot take it, drop your item first.");
                }
            }
        }
        else {          // Case : No item in this room
            System.out.println("No items in the room.");
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
