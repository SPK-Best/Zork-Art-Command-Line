package zork_game;

public abstract class Actor {

    private int maxHp;
    private int hp;
    protected int attackPower;
    private String name;

    public Actor() {
        initialize(0,0,"");
    }

    public Actor(int maxHp, int attack, String name) {
        initialize(maxHp, attack, name);
    }

    // Setting stat of actor which don't have the current hp
    public void initialize(int maxHp, int attackPower, String name) {
        initialize(maxHp, maxHp, attackPower, name);
    }

    // Set stat of actor
    protected void initialize(int maxHp, int hp, int attackPower, String name) {
        this.maxHp = maxHp;
        this.hp = hp;
        this.attackPower = attackPower;
        this.name = name;
    }

    public void decreaseHP(int deltaHp) {
        hp -= deltaHp;
        hp = Math.max(hp, 0);
    }

    public void increaseHP(int deltaHp) {
        hp += deltaHp;
        hp = Math.min(hp, maxHp);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public String getName() {
        return name;
    }

    // Decrease this actor's hp if it is attacked
    public void isHitted(int attackPower) {
        System.out.printf("%s got attacked\n", name);
        this.decreaseHP(attackPower);
        System.out.printf("%s HP: %d/%d\n", name, hp, maxHp);
    }

}