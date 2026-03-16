package com.taller.patrones.application.subscribers;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;

public class StatsUpdator implements Subscriber {
    public void applyDamageUpdate(Battle battle, Character attacker, Character defender, int damage, Attack attack){
        defender.takeDamage(damage);
        String target = defender == battle.getPlayer() ? "player" : "enemy";
        battle.setLastDamage(damage, target);
        battle.switchTurn();
        if (!defender.isAlive()) {
            battle.finish(attacker.getName());
        }
    }

    public void unapplyDamageUpdate(Battle battle, Character defender, int damage){
        defender.takeDamage(-damage);
        battle.switchTurn();
    }
}
