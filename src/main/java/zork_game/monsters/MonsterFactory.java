package zork_game.monsters;

public class MonsterFactory {

    public Monster makeMonster(String name, int hp, int attackPower){
        return new Monster(name, hp, attackPower);
    }

    public Zork makeZork(){
        return new Zork();
    }

    public Orc makeOrc() {return new Orc();}
}
