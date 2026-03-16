package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class IceBeamAttackFactory implements AttackTypeFactory {
    public Attack createAttack(){
        return new Attack("Ice Beam", 70, Attack.AttackType.SPECIAL);
    }
}
