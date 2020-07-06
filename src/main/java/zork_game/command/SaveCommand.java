package zork_game.command;

import zork_game.Game;

public class SaveCommand extends Command {

    private Game game;

    public SaveCommand(Game game){
        super("Save current status","save {savename}", 1);
        this.game = game;
    }

    @Override
    public void apply() {
        String mapFilename = "./src/main/java/zork_game/save/map" + getParameter() + ".txt";
        String playerFilename = "./src/main/java/zork_game/save/player" + getParameter() + ".txt";
        boolean mapStatus = game.map.saveMap(mapFilename, getParameter());
        boolean playerStatus = game.player.savePlayer(playerFilename, getParameter());
        if(mapStatus && playerStatus) {
            System.out.println("Save to "+ getParameter() + " completed!");
        }
        else {
            System.out.println("Nothing saved");
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
