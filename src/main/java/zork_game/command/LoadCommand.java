package zork_game.command;

import zork_game.Game;

public class LoadCommand extends Command {

    private Game game;

    public LoadCommand(Game game){
        super("load a save game", "load {savename}", 1);
        this.game = game;
    }

    @Override
    public void apply() {
        System.out.println("----------");
        String mapFilename = "./src/main/java/zork_game/save/map" + getParameter() + ".txt";
        String playerFilename = "./src/main/java/zork_game/save/player" + getParameter() + ".txt";
        boolean mapStatus = game.map.loadMap(mapFilename);
        boolean playerStatus = game.player.loadPlayer(playerFilename);
        if(mapStatus && playerStatus) {
            System.out.printf("Load %s Successfully !!!\n", getParameter());
            game.setStartPlay();
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.HOME_STATUS ;
    }
}
