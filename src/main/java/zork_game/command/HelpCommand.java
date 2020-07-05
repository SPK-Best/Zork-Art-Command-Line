package zork_game.command;

import zork_game.Game;

public class HelpCommand extends Command {

    public HelpCommand(){
        super("Show All Commands","help",0);
    }

    @Override
    protected void apply() {
        System.out.println("----------");
        System.out.println("All Commands:");
        for (String command: Game.commands.keySet()){
            System.out.println(command + " - " + Game.getCommand(command).description);
        }
        System.out.println("----------");
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.HOME_STATUS || status == Game.PLAY_STATUS;
    }
}
