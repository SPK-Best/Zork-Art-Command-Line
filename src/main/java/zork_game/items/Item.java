package zork_game.items;

public class Item {

    private String itemName;
    private String itemType;

    public Item(String itemName) {
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public Item(String itemName,String itemType) {
        super();
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }
}