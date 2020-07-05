package zork_game;

import zork_game.command.Command;
import zork_game.command.MapCommand;
import zork_game.items.Item;
import zork_game.monsters.Monster;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    /**
     * Tell the status of the game
     * - HOME_STATUS -> Do not start the game yet (Can load)
     * - PLAY_STATUS -> Start play the game
     */
    public static final boolean PLAY_STATUS = false;
    public static final boolean HOME_STATUS = true;

    public Player player;
    public Map map;
    private static boolean gameStatus;   // Tell the status of the current program (Home or Play)

    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public Game() {
        setStartMenu();          // Print welcome message
        generateCommands();	     // Prepare all commands
        map = new Map();		 // Create new Map
        player = new Player();	 // Create new Player
    }

    /**
     * Generate all commands available for a player
     */
    private void generateCommands() {
        commands.put("map", new MapCommand(this));
    }

    /**
     * Get the command from Hashmap
     */
    public static Command getCommand(String name) {
        return commands.get(name);
    }

    /**
     * Use input command
     */
    public void oneTurn(String fullCommand) {
        Command command = getCommand(fullCommand.split(" ")[0]);

        if (command == null) {        // Case : Unknown command or no input command
            System.out.println("Unknown command ");
        }
        else if (!command.canUse(gameStatus)) {		// Check for status of the game and command that can be used (Ex. Cannot use attack command while Player do not start the game)
            System.out.println("Cannot use this command in this state");
        }
        else {
            command.apply(fullCommand);
        }
    }

    /**
     * Keep waiting for player's input (Command)
     */
    public void run() {
        while(true) {
            Scanner input = new Scanner(System.in);
            boolean status = true;

            while (status) {
                System.out.print(">: ");
                String command = input.nextLine();
                oneTurn(command);
            }
        }
    }

    /**
     * Set the player's status to be play
     */
    public void setStartPlay() {
        gameStatus = PLAY_STATUS;
    }

    /**
     * Print the message to player
     */
    public void setStartMenu() {
        System.out.println("Welcome to Zork Game!!!");
        System.out.println("* Type \"play {map-name}\" to start a new game with chosen map");
        System.out.println("* Type \"load {save-point-name}\" to play a saved game");
        System.out.println("* Type \"exit\" to exit the game");
        gameStatus = HOME_STATUS;
    }

}
