package zork_game;

import zork_game.items.Item;
import zork_game.items.weapons.Weapon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Player extends Actor {

    public static final int INCREASE_ATTACK_POWER = 2;     // Increase attack power every time Player win the battle
    public static final int INCREASE_HP = 5;               // Increase Hp every time Player walk to other rooms

    public Item item;
    public Weapon weapon;
    private int posX;
    private int posY;

    public Player(){
        super(0,0, "You");
        item = null;
    }

    /**
     * Load a player status form filename used when load file
     * Ex. 0 0 150 150 10
     * line[0] = 0 (X Position)
     * line[1] = 0 (Y Position)
     * line[2] = 150 (Current Hp)
     * line[3] = 150 (Max Hp)
     * line[4] = 10 (Attack Power)
     */
    public boolean loadPlayer(String filename) {
        try {
            Path filePath = new File(filename).toPath();
            Charset charset = Charset.defaultCharset();
            List<String> stringList = Files.readAllLines(filePath, charset);
            String[] stringArray = stringList.toArray(new String[]{});

            String[] line = stringArray[0].split(" ");
            posX = Integer.parseInt(line[0]);
            posY = Integer.parseInt(line[1]);
            int hp = Integer.parseInt(line[2]);
            int maxHp = Integer.parseInt(line[3]);
            int attackPower = Integer.parseInt(line[4]);
            initialize(hp, maxHp, attackPower, "You");    // Initialize Player with current status
        }
        catch (IOException e) {
            System.out.println("Error: Cannot load this player file");
            return false;
        }
        return true;
    }

    public void increaseAttackPower() {
        attackPower += INCREASE_ATTACK_POWER;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private void move(int delX, int delY) {
        posX += delX;
        posY += delY;
        increaseHP(INCREASE_HP);
    }

    public void moveNorth() {
        move(-1,0);
    }

    public void moveEast() {
        move(0,1);
    }

    public void moveSouth() {
        move(1,0);
    }

    public void moveWest() {
        move(0,-1);
    }

    public boolean isCarryItem() {
        return item != null;
    }

    public boolean isCarryWeapon() {
        return weapon != null;
    }

    public void carryWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void carryItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public Item dropItem() {
        Item droppedItem = getItem();
        item = null;
        return droppedItem;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Weapon dropWeapon() {
        Weapon droppedWeapon = getWeapon();
        weapon = null;
        return droppedWeapon;
    }

    public int getWeaponAttackPower() {
        if(isCarryWeapon()) {
            return getWeapon().getPowerVolume();
        }
        return 0;
    }

    /**
     * Save current Player status to the file
     * Ex. 3 2 100 150 20
     * It means that Player is in position (3,2) with Hp:100/150, attack power: 20
     */
    public boolean savePlayer(String filename, String saveName) {
        String text = Integer.toString(getPosX()) + " " + Integer.toString(getPosY()) + " " + Integer.toString(getHp()) + " " + Integer.toString(getMaxHp()) + " " +Integer.toString(getAttackPower());

        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf(text);
            printWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: cannot save due to error filename");
            return false;
        }
        return true;
    }

}
