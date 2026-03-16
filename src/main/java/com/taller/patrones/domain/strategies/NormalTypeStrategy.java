package com.taller.patrones.domain.strategies;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Character;

public class NormalTypeStrategy implements AttackTypeStrategy{
    public int calculateDamage(Character attacker, Character defender, Attack attack){
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        return Math.max(1, raw - defender.getDefense());
    }
}

