package com.taller.patrones.interfaces.rest.adapters;
import com.taller.patrones.domain.Character;
import java.util.Map;

public class StartBattleRequestAdapter {

    /**
     * Más cómodo usar constantes para los stirngs, si en el futuro cambia el nombre del parámetro, sólo cambias esto
     * y todas las clases que lo referenciaran se actualizan solas.
     */
    private static final String FIGHTER1_NAME = "fighter1_name";
    private static final String FIGHTER1_HP = "fighter1_hp";
    private static final String FIGHTER1_ATK = "fighter1_atk";
    private static final String FIGHTER2_NAME = "fighter2_name";
    private static final String FIGHTER2_HP = "fighter2_hp";
    private static final String FIGHTER2_ATK = "fighter2_atk";
    //también podrías crear variables para los números... depende de la situación. Yo lo suelo hacer siempre con strings porque
    //luego para los tests también es más cómodo.

    /**
     * No está mal que lo hayas hecho estático, ¿ha sido a posta? ¿Has pensado en las ventajas/incovenientes?
     */
    public static Character getPlayer(Map<String, Object> body){
        String fighter1Name = (String) body.getOrDefault(FIGHTER1_NAME, "Héroe");
        int fighter1Hp = ((Number) body.getOrDefault(FIGHTER1_HP, 150)).intValue();
        int fighter1Atk = ((Number) body.getOrDefault(FIGHTER1_ATK, 25)).intValue();
        return new Character(fighter1Name, fighter1Hp, fighter1Atk, 10, 10);
    }

    /**
     * No está mal que lo hayas hecho estático, ¿ha sido a posta? ¿Has pensado en las ventajas/incovenientes?
     */
    public static Character getEnemy(Map<String, Object> body){
        String fighter2Name = (String) body.getOrDefault(FIGHTER2_NAME, "Dragón");
        int fighter2Hp = ((Number) body.getOrDefault(FIGHTER2_HP, 120)).intValue();
        int fighter2Atk = ((Number) body.getOrDefault(FIGHTER2_ATK, 30)).intValue();
        return new Character(fighter2Name, fighter2Hp, fighter2Atk, 10, 10);
    }

    //Le podrías dar una vuelta más y refactorizar para sacar el código en común, ¿lo has pensado? Cuantas menos líneas en una clase, mejor
}
