package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class ThunderAttackFactory implements AttackTypeFactory {
    public Attack createAttack(){
        return new Attack("Thunder", 90, Attack.AttackType.SPECIAL);
    }
}
