package zork_game.command;

import zork_game.Game;

public class QuitCommand extends Command {

    private Game game;

    public QuitCommand(Game game){
        super("Quit the game", "quit", 0);
        this.game = game;
    }

    @Override
    protected void apply() {
        System.out.println("Quit game and return to start menu...\n");
        game.setStartMenu();
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
