package zork_game.command;

import zork_game.Game;

public class ExitCommand extends Command {

    public ExitCommand(){
        super("Exit the game","exit",0);
    }

    @Override
    public void apply() {
        System.out.println("----------");
        System.out.println("Exit Game");
        System.exit(0);
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.HOME_STATUS ;
    }
}
