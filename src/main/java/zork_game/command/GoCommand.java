package zork_game.command;

import zork_game.Game;
import zork_game.Room;

public class GoCommand extends Command {

    private Game game;

    public GoCommand(Game game){
        super("Go to the adjacency room", "go {direction}", 1);
        this.game = game;
    }

    @Override
    public void apply() {
        System.out.println("----------");
        String direction = getParameter();
        if(!direction.equals("N") && !direction.equals("E") && !direction.equals("S") && !direction.equals("W")) {
            System.out.println("Unknown direction, please use (N/E/S/W)");
        }
        else {
            Room currentRoom = game.map.getRoom(game.player.getPosX(),game.player.getPosY());

            if(direction.equals("N") && !currentRoom.hasNWall()) {         // Can move to North room
                game.player.moveNorth();
            }
            else if(direction.equals("E") && !currentRoom.hasEWall()) {    // Can move to East room
                game.player.moveEast();
            }
            else if(direction.equals("S") && !currentRoom.hasSWall()) {   // Can move to South room
                game.player.moveSouth();
            }
            else if(direction.equals("W") && !currentRoom.hasWWall()) {  // Can move to West room
                game.player.moveWest();
            }
            else {  	// Cannot move
                System.out.println("Cannot move, struck the wall");
            }
        }
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
