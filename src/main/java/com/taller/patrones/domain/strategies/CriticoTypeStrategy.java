package com.taller.patrones.domain.strategies;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Character;

public class CriticoTypeStrategy {
    public int calculateDamage(Character attacker, Character defender, Attack attack){
        return Math.toIntExact(Math.round(attack.getBasePower() * 1.5));
    }
}
