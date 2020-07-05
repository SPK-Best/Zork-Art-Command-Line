package zork_game.command;

import zork_game.Game;
import zork_game.Player;
import zork_game.Room;

public class InfoCommand extends Command {

    private Game game;

    public InfoCommand(Game game){
        super("Show Player Stats and Room Information","info", 0);
        this.game = game;
    }

    @Override
    public void apply() {
        Player player = game.player;
        System.out.println("----------");
        System.out.println("Player Stats");
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Attack Power: " + player.getAttackPower());
        if(player.isCarryWeapon()) {
            System.out.println("You carry "+player.getWeapon().getItemName());
        } else {
            System.out.println("You carry no weapon.");
        }
        if(player.isCarryItem()) {
            System.out.println("You carry "+player.getItem().getItemName());
        } else {
            System.out.println("You carry no item.");
        }
        System.out.format("Current Position: (%d,%d)\n", game.player.getPosX(), game.player.getPosY());
        Room curRoom = game.map.getRoom(game.player.getPosX(),game.player.getPosY());
        if(curRoom.isItemExists()) {
            System.out.format("There is %s in this room\n", curRoom.getItem().getItemName());
        }else {
            System.out.println("There is no item in this room");
        }
        if(curRoom.isMonsterExist()) {
            System.out.format("There is %s in this room\n", curRoom.getMonster().getName());
        }else {
            System.out.println("There is no monster in this room");
        }
        System.out.println("----------");
    }

    @Override
    public boolean canUse(boolean status) {
        return status == Game.PLAY_STATUS ;
    }
}
