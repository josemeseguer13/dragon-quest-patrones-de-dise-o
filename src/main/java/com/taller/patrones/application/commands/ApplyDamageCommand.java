package com.taller.patrones.application.commands;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import com.taller.patrones.application.subscribers.Subscriber;
import lombok.Builder;

import java.util.List;

@Builder
public class ApplyDamageCommand implements Command{
    private final Battle battle;
    private final Character attacker;
    private final Character defender;
    private final int damage;
    private final Attack attack;
    private List<Subscriber> observers;

    @Override
    public void execute() {
        observers.forEach(sub -> sub.applyDamageUpdate(battle,attacker, defender, damage, attack));
    }

    @Override
    public void undo() {
        observers.forEach(sub -> sub.unapplyDamageUpdate(battle, attacker, damage));
    }
}
