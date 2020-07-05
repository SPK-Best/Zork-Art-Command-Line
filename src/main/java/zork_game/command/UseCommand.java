package zork_game.command;

import zork_game.Game;
import zork_game.items.Item;

public class UseCommand extends Command {

    private Game game;

    public UseCommand(Game game){
        super("Use an item that player carries","use", 0);
        this.game = game;
    }

    @Override
    public void apply() {
        if(game.player.isCarryItem()) {
            Item item = game.player.dropItem();
            System.out.format("You use %s\n", item.getItemName());
            item.use(game.player);
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
