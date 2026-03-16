package com.taller.patrones.interfaces.rest.adapters;
import com.taller.patrones.domain.Character;
import java.util.Map;

public class StartBattleRequestAdapter {

    public static Character getPlayer(Map<String, Object> body){
        String fighter1Name = (String) body.getOrDefault("fighter1_name", "Héroe");
        int fighter1Hp = ((Number) body.getOrDefault("fighter1_hp", 150)).intValue();
        int fighter1Atk = ((Number) body.getOrDefault("fighter1_atk", 25)).intValue();
        return new Character(fighter1Name, fighter1Hp, fighter1Atk, 10, 10);
    }

    public static Character getEnemy(Map<String, Object> body){
        String fighter2Name = (String) body.getOrDefault("fighter2_name", "Dragón");
        int fighter2Hp = ((Number) body.getOrDefault("fighter2_hp", 120)).intValue();
        int fighter2Atk = ((Number) body.getOrDefault("fighter2_atk", 30)).intValue();
        return new Character(fighter2Name, fighter2Hp, fighter2Atk, 10, 10);
    }
}
