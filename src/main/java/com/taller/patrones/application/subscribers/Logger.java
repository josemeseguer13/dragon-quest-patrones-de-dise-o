package com.taller.patrones.application.subscribers;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;

public class Logger implements Subscriber {
    public void applyDamageUpdate(Battle battle, Character attacker, Character defender, int damage, Attack attack){
        battle.log(attacker.getName() + " usa " + attack.getName() + " y hace " + damage + " de daño a " + defender.getName());
    }

    @Override
    public void unapplyDamageUpdate(Battle battle, Character defender, int damage){}
}
