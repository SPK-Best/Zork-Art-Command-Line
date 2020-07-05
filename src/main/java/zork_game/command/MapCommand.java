package zork_game.command;

import zork_game.Game;

public class MapCommand extends Command{

    private Game game;

    public MapCommand(Game game){
        super("Show map", "map", 0);
        this.game = game;
    }

    @Override
    public void apply() {
        System.out.println("----------");
        game.map.showMap(game.player.getPosX(),game.player.getPosY());
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
