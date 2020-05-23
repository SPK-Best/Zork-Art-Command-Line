package zork_game;

import zork_game.command.Command;
import zork_game.command.HelpCommand;
import zork_game.command.InfoCommand;
import zork_game.command.QuitCommand;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    public static Player player;

    public static final HashMap<String, Command> commands = new HashMap<String, Command>() {
        {
            put("help", new HelpCommand());
            put("quit", new QuitCommand());
            put("info", new InfoCommand());
        }
    };

    public static Command getCommand(String name) {
        return commands.get(name);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        player = new Player();

            while (!quit) {
                System.out.print(">: ");
                String commandLine = scanner.nextLine();
                Command command = Game.getCommand(commandLine);

                if (command == null) {
                    System.out.println("Unknown command ");
                }
                else {
                    command.apply();
                }
            }
            quit = false;
        }
}
