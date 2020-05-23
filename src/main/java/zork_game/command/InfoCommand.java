package zork_game.command;

import zork_game.Game;

public class InfoCommand extends Command {
    public InfoCommand(){
        description = "Show Player Stats and Room Info";
    }

    @Override
    public void apply() {
        System.out.println("----------");
        System.out.println("Player Stats");
        System.out.println("HP: " + Game.player.hp + "/" + Game.player.MAX_HP);
        System.out.println("Attack Power: " + Game.player.attackPower);

        if (Game.player.item != null) {
            System.out.println("Carrying " + Game.player.item.getItemName() + " (" + Game.player.item.getItemType() + ")");
        }
        System.out.println("----------");
    }
}
