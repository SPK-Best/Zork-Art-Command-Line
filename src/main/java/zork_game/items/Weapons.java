package zork_game.items;

public class Weapons extends Item {
    int attackPower;

    public Weapons(String name, int AttackPower){
        this.itemName = name;
        this.attackPower = AttackPower;
    }
}
