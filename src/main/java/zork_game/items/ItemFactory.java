package zork_game.items;

import java.util.HashMap;
import java.util.Map;

public class ItemFactory {

    private static Map<String, Class> itemClassMap = new HashMap<String, Class>() {{
        ItemType[] itemTypes = ItemType.values();
        for(int i = 0;i < itemTypes.length; i++) {
            put(itemTypes[i].getItemName(), itemTypes[i].getItemClass());
        }
    }};

    private static Map<String, Integer> itemPowerVolumeMap = new HashMap<String, Integer>() {{
        ItemType[] itemTypes = ItemType.values();
        for(int i = 0;i < itemTypes.length; i++) {
            put(itemTypes[i].getItemName(), itemTypes[i].getItemPowerVolume());
        }
    }};

    public static Item makeItem(String itemName) {
        Class itemClass = itemClassMap.get(itemName);
        int itemPowerVolume = itemPowerVolumeMap.get(itemName);
        if(itemClass != null) {
            try {
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
        throw new IllegalArgumentException("Unknown itemType");
    }

}
