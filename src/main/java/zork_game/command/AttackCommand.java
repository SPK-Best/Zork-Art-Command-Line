package zork_game.command;

import zork_game.Game;
import zork_game.Room;

public class AttackCommand extends Command {

    private Game game;

    public AttackCommand(Game game){
        super("attack a monster in this room", "attack", 0);
        this.game = game;
    }

    @Override
    public void apply() {
        Room curRoom = game.map.getRoom(game.player.getPosX(), game.player.getPosY());		// Get the position of the current room
        if(curRoom.isMonsterExist()) {														// Case : There is a monster in this room
            boolean result = game.fight(curRoom.getMonster()); //true=win, false=lose
            if(result) {																	// Case : Player win the fight
                game.map.reduceNbOfMonsterByOne();
                curRoom.removeMonster();
                System.out.println("\nYour attack power increase!!");
                game.player.increaseAttackPower();
                if(game.map.isAllMonsterBeKilled()) {										// Case : All monsters are killed (Player win this game)
                    System.out.println("\nCongratulation!!");
                    System.out.println("You kill all of monsters, You win\n");
                    game.setStartMenu();
                }
            }
            else {																// Case : Player lose the fight
                System.out.println("You die!!");
                System.out.println("Going back to main menu\n");

                game.setStartMenu();
            }
        }
        else {																// Case : No monster in this room
            System.out.println("There is no monster to fight here.");
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }

}
