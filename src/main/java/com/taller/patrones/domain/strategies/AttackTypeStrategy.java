package com.taller.patrones.domain.strategies;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Character;

public interface AttackTypeStrategy {
    int calculateDamage(Character attacker, Character defender, Attack attack);
}
