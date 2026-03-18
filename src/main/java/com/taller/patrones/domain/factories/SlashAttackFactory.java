package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

public class SlashAttackFactory implements AttackFactory {
    public Attack createAttack(){
        return new Attack("Slash", 55, Attack.AttackType.NORMAL);
    }
}
