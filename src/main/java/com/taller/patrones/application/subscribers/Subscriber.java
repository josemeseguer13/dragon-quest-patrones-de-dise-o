package com.taller.patrones.application.subscribers;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;

public interface Subscriber {
    void applyDamageUpdate(Battle battle, Character attacker, Character defender, int damage, Attack attack);
    void unapplyDamageUpdate(Battle battle, Character defender, int damage);
}
