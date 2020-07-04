package zork_game.monsters;

import java.util.HashMap;
import java.util.Map;

public class MonsterFactory {

    private static Map<String, Class> monsterClassMap = new HashMap<String, Class>() {{
        MonsterType[] monsterTypes = MonsterType.values();
        for(int i=0;i<monsterTypes.length;i++) {
            put(monsterTypes[i].getMonsterName(), monsterTypes[i].getMonsterClass());
        }
    }};

    private static Map<String, Integer> monsterMaxHpMap = new HashMap<String, Integer>() {{
        MonsterType[] monsterTypes = MonsterType.values();
        for(int i=0;i<monsterTypes.length;i++) {
            put(monsterTypes[i].getMonsterName(), monsterTypes[i].getMonsterMaxHp());
        }
    }};

    private static Map<String, Integer> monsterAttackPowerMap = new HashMap<String, Integer>() {{
        MonsterType[] monsterTypes = MonsterType.values();
        for(int i=0;i<monsterTypes.length;i++) {
            put(monsterTypes[i].getMonsterName(), monsterTypes[i].getMonsterAttackPower());
        }
    }};


    public static Monster createMonster(String monsterName) {
        Class monsterClass = monsterClassMap.get(monsterName);
        int monsterMaxHp = monsterMaxHpMap.get(monsterName);
        int monsterAttackPowerHp = monsterAttackPowerMap.get(monsterName);
        if(monsterClass != null) {
            try {
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
        throw new IllegalArgumentException("Unknown monsterType");
    }

}