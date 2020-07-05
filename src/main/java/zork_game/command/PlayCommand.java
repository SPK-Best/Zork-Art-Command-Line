package zork_game.command;

import zork_game.Game;

public class PlayCommand extends Command {

    private Game game;

    public PlayCommand(Game game){
        super("Play a new game with chosen map", "play {mapname}", 1);
        this.game = game;
    }

    @Override
    public void apply() {
        System.out.println("----------");
        System.out.format("Play Game %s\n", getParameter());
        String mapFilename = "./src/main/java/zork_game/map/map"+ getParameter()+".txt";
        String playerFilename = "./src/main/java/zork_game/map/player"+ getParameter()+".txt";
        boolean mapStatus = game.map.loadMap(mapFilename);
        boolean playerStatus = game.player.loadPlayer(playerFilename);
        if(mapStatus && playerStatus)
            game.setStartPlay();
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.HOME_STATUS ;
    }
}
