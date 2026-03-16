package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class TackleAttackFactory implements AttackTypeFactory {
    public Attack createAttack(){
        return new Attack("Tackle", 40, Attack.AttackType.NORMAL);
    }
}
