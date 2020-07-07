package zork_game.items;

import java.util.HashMap;
import java.util.Map;

public class ItemFactory {

    /**
     * Collect the class of each item
     * Ex. (pistol, Pistol.class)
     *     (large_potion, LargePotion.class)
     */
    private static Map<String, Class> itemClassMap = new HashMap<String, Class>() {{
        ItemType[] itemTypes = ItemType.values();
        for(int i = 0;i < itemTypes.length; i++) {
            put(itemTypes[i].getItemName(), itemTypes[i].getItemClass());
        }
    }};

    /**
     * Collect the volume of each item
     * Ex. (pistol, 30)
     *     (large_potion, 50)
     */
    private static Map<String, Integer> itemPowerVolumeMap = new HashMap<String, Integer>() {{
        ItemType[] itemTypes = ItemType.values();
        for(int i = 0;i < itemTypes.length; i++) {
            put(itemTypes[i].getItemName(), itemTypes[i].getItemPowerVolume());
        }
    }};

    /**
     * Make an item
     */
    public static Item makeItem(String itemName) {
        Class itemClass = itemClassMap.get(itemName);               // Get item class
        int itemPowerVolume = itemPowerVolumeMap.get(itemName);     // Get the volume
        if(itemClass != null) {
            try {                                                   // Create item
                Item item = (Item) itemClass.newInstance();
                item.initialize(itemName,itemPowerVolume);
                return item;
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch(IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Unknown itemType");      // Unknown Item
    }

}
