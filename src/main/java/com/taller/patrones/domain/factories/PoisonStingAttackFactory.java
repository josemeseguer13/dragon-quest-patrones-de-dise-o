package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class PoisonStingAttackFactory implements AttackFactory {
    public Attack createAttack(){
        return new Attack("Poison Sting", 20, Attack.AttackType.STATUS);
    }
}
