package zork_game.monsters;

public class Monster {
    public String name;
    public boolean alive;
    public int hp;
    public int attackPower;

    public Monster(String name, int hp, int attackPower){
        alive = true;
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public void HitByPlayer(int damage){
        this.hp -= damage;
        if (this.hp <= 0){
            this.alive = false;
        }
    }
}
