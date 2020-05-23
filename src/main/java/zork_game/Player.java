package zork_game;

import zork_game.items.Item;

public class Player {
    public boolean alive;
    public int hp;
    public static final int MAX_HP = 100;
    public int attackPower;
    public Item item;

    public Player(){
        alive = true;
        hp = MAX_HP;
        attackPower = 10;
        item = null;
    }

    public void increaseHp(int num){

        if (this.hp + num > MAX_HP || MAX_HP == 100){    // Hp is already full or nearly full
            this.hp = MAX_HP;
        }
        else{
            this.hp += num;
        }
    }

    public void decreaseHp(int num){
        this.hp -= num;

        if (this.hp <= 0){   // Player died
            this.alive = false;
        }
    }
}
