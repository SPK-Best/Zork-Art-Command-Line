package zork_game.command;

public class QuitCommand extends Command {
    public QuitCommand(){
        description = "Quit the Game";
    }

    @Override
    public void apply() {
        System.exit(0);     // Exit the program
    }
}
