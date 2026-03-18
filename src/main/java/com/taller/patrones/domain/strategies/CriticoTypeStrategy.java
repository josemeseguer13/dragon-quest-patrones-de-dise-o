package com.taller.patrones.domain.strategies;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Character;

public class CriticoTypeStrategy {
    public int calculateDamage(Character attacker, Character defender, Attack attack){

        // 20% de probabilidad
        if (Math.random() > 0.2) {
            return 0; // el ataque falla
        } //Creo que te falta esto para que funcione el 20% de las veces


        return Math.toIntExact(Math.round(attack.getBasePower() * 1.5));
    }
}
