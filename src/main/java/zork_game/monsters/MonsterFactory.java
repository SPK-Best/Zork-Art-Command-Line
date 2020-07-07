package zork_game.monsters;

import java.util.HashMap;
import java.util.Map;

public class MonsterFactory {

    /**
     * Collect the class of each monster
     * Ex. (giant, Giant.class)
     *     (orc, Orc.class)
     */
    private static Map<String, Class> monsterClassMap = new HashMap<String, Class>() {{
        MonsterType[] monsterTypes = MonsterType.values();
        for(int i = 0;i < monsterTypes.length; i++) {
            put(monsterTypes[i].getMonsterName(), monsterTypes[i].getMonsterClass());
        }
    }};

    /**
     * Collect the Hp of each monster
     * Ex. (giant, 150)
     *     (orc, 100)
     */
    private static Map<String, Integer> monsterMaxHpMap = new HashMap<String, Integer>() {{
        MonsterType[] monsterTypes = MonsterType.values();
        for(int i = 0;i < monsterTypes.length; i++) {
            put(monsterTypes[i].getMonsterName(), monsterTypes[i].getMonsterMaxHp());
        }
    }};

    /**
     * Collect the attack power of each monster
     * Ex. (giant, 25)
     *     (orc, 10)
     */
    private static Map<String, Integer> monsterAttackPowerMap = new HashMap<String, Integer>() {{
        MonsterType[] monsterTypes = MonsterType.values();
        for(int i = 0;i < monsterTypes.length; i++) {
            put(monsterTypes[i].getMonsterName(), monsterTypes[i].getMonsterAttackPower());
        }
    }};

    /**
     * Make a monster
     */
    public static Monster createMonster(String monsterName) {
        Class monsterClass = monsterClassMap.get(monsterName);              // Get monster class
        int monsterMaxHp = monsterMaxHpMap.get(monsterName);                // Get monster Hp
        int monsterAttackPowerHp = monsterAttackPowerMap.get(monsterName);  // Get monster attack power
        if(monsterClass != null) {
            try {                                                           // Create monster
                Monster monster = (Monster) monsterClass.newInstance();
                monster.initialize(monsterMaxHp,monsterAttackPowerHp, monsterName);
                return monster;
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch(IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Unknown monsterType");          // Unknown monster
    }

}