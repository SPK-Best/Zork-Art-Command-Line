package zork_game.items;

import zork_game.Player;

public abstract class Item {

    private String itemName;
    private int powerVolume;

    public String getItemName() {
        return itemName;
    }

    protected abstract String ability();

    public void initialize(String itemName, int powerVolume) {
        this.itemName = itemName;
        this.powerVolume = powerVolume;
    }

    public int getPowerVolume() {
        return powerVolume;
    }

    public abstract void use(Player player);

}