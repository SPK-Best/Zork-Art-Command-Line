package zork_game.items;

import zork_game.items.weapons.*;
import zork_game.items.potions.*;

/**
 * All types of items in this game
 */
public enum ItemType {

    AXE(10, Axe.class, "axe"),
    PISTOL(30, Pistol.class, "pistol"),
    SWORD(15, Sword.class, "sword"),
    LARGEPOTION(50,LargePotion .class, "large_potion"),
    MEDIUMPOTION(30,MediumPotion .class, "medium_potion"),
    SMALLPOTION(10,SmallPotion .class, "small_potion");

    private int itemPowerVolume;
    private Class itemClass;
    private String itemName;

    ItemType(int itemPowerVolume, Class itemClass, String itemName){
        this.itemPowerVolume = itemPowerVolume;
        this.itemClass = itemClass;
        this.itemName = itemName;
    }

    public int getItemPowerVolume() {
        return itemPowerVolume;
    }

    public Class getItemClass() {
        return itemClass;
    }

    public String getItemName() {
        return itemName;
    }

}
