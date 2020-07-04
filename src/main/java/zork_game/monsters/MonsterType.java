package zork_game.monsters;

public enum  MonsterType {

    GIANT(300, 50, Giant.class, "giant"),
    ORC(100, 10, Orc.class, "orc"),
    ZORK(100, 20, Zork.class, "zork");

    private int monsterMaxHp;
    private int monsterAttackPower;
    private Class monsterClass;
    private String monsterName;

    MonsterType(int monsterMaxHp, int monsterAttackPower, Class monsterClass, String monsterName){
        this.monsterMaxHp = monsterMaxHp;
        this.monsterAttackPower = monsterAttackPower;
        this.monsterClass = monsterClass;
        this.monsterName = monsterName;
    }

    public int getMonsterMaxHp() {
        return monsterMaxHp;
    }

    public int getMonsterAttackPower() {
        return monsterAttackPower;
    }

    public Class getMonsterClass() {
        return monsterClass;
    }

    public String getMonsterName() {
        return monsterName;
    }
}
