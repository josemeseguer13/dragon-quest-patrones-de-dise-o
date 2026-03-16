package com.taller.patrones.domain.strategies;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Character;

public class SpecialTypeStrategy {
    public int calculateDamage(com.taller.patrones.domain.Character attacker, Character defender, Attack attack){
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        int effectiveDef = defender.getDefense() / 2;
        return Math.max(1, raw - effectiveDef);
    }
}
