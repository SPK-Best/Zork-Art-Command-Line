package zork_game.items;

import zork_game.items.weapons.Axe;
import zork_game.items.weapons.Pistol;
import zork_game.items.weapons.Sword;
import zork_game.items.weapons.Weapons;

public class ItemFactory {

    public Weapons makeWeapon(String name, int addedAttackPower){
        return new Weapons(name, addedAttackPower);
    }

    public Sword makeSword(){
        return new Sword();
    }

    public Axe makeAxe(){
        return new Axe();
    }

    public Pistol makePistol() { return new Pistol(); }
}
