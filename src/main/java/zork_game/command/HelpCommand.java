package zork_game.command;

import zork_game.Game;

public class HelpCommand extends Command {
    public HelpCommand(){
        description = "All commands";
    }

    @Override
    public void apply() {
        System.out.println("----------");
        System.out.println("All Commands:");
        for (String command: Game.commands.keySet()){
            System.out.println(command + " - " + Game.getCommand(command).description);
        }
        System.out.println("----------");
    }
}
