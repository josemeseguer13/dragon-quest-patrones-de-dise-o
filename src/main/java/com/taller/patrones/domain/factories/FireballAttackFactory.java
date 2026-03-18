package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class FireballAttackFactory implements AttackFactory {
    public Attack createAttack(){
        return new Attack("Fireball", 80, Attack.AttackType.SPECIAL);
    }
}
