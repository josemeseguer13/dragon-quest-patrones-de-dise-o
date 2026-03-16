package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class MeteorAttackFactory implements AttackTypeFactory{
    public Attack createAttack(){
        return new Attack("Meteoro", 120, Attack.AttackType.SPECIAL);
    }
}
