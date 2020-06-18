package zork_game.command;

import zork_game.Game;

public class TakeCommand extends Command {

    public TakeCommand() {description = "Picks up an item in the current room"; }

    public void apply() {
        if (Game.currentRoom.item != null && Game.player.item == null) {
            Game.player.item = Game.currentRoom.item;
            Game.currentRoom.item = null;
            System.out.println("Picked up " + Game.player.item.itemName);
        }
        else {
            System.out.println("No items in the room.");
        }
    }
}
