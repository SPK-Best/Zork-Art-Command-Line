package zork_game;

import zork_game.command.*;
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
        commands.put("attack", new AttackCommand(this));
        commands.put("exit", new ExitCommand());
        commands.put("go", new GoCommand(this));
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(this));
        commands.put("map", new MapCommand(this));
        commands.put("play", new PlayCommand(this));
        commands.put("quit", new QuitCommand(this));
        commands.put("use", new UseCommand(this));
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
        System.out.println("* Type \"help\" to print all commands");
        System.out.println("* Type \"exit\" to exit the game");
        gameStatus = HOME_STATUS;
    }

    /**
     * Fight situation (After use attack command)
     */
    public boolean fight(Monster monster) {
        Scanner sc = new Scanner(System.in);
        boolean fightState = true; // Use to switch the turn (true: playerTurn, false: monsterTurn)
        while(true) {

            // Player turn
            if(player.isAlive()) {
                System.out.println("\n### YOUR TURN ###");
                System.out.printf("Your HP: %d/%d\n", player.getHp(), player.getMaxHp());
                if(player.isCarryWeapon()) {
                    System.out.println("You carry "+player.getWeapon().getItemName());
                }
                else {
                    System.out.println("You carry no weapon.");
                }

                if(player.isCarryItem()) {
                    System.out.println("You carry "+ player.getItem().getItemName());
                }
                else {
                    System.out.println("You carry no item.");
                }

                // Print the detail of monster
                System.out.println("\n### Monster Detail ###");
                System.out.format("%s HP: %d/%d\n", monster.getName(), monster.getHp(), monster.getMaxHp());
                System.out.println("\nPlease choose action: \"attack\", \"item\", \"skip\"");

                System.out.print(">: ");
                String command = sc.nextLine();

                // Attack: player attack a monster (monster has a probability of evading)
                if(command.equals("attack")) {
                    int attackPower = player.getAttackPower() + player.getWeaponAttackPower();
                    monster.isHitted(attackPower);
                    fightState = !fightState;
                }
                else if(command.equals("item")) {           // Item: use an item
                    if(player.isCarryItem()) {
                        Item item = player.dropItem();
                        System.out.format("You use %s\n", item.getItemName());
                        item.use(player);
                        fightState = !fightState;
                    }
                    else {
                        System.out.format("You carry nothing.");
                    }
                }
                else if(command.equals("skip")) {           // Skip: skip your turn
                    System.out.println("You skip this turn");
                    fightState = !fightState;
                }
                else                // Otherwise (Unknown command)
                    System.out.println("Error: Unrecognized command");
            }
            else {
                return false;
            }

            // Monster turn
            if(monster.isAlive()) {     // Case : Monster attacks user
                System.out.format("\n### %s TURN ###\n", monster.getName());
                int attackPower = monster.getAttackPower();
                player.isHitted(attackPower);
                fightState = !fightState;
            }
            else {  		// Case : Monster is killed
                System.out.format("you kill %s", monster.getName());
                return true;
            }
        }
    }
}