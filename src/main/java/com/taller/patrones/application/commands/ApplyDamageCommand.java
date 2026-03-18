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

    /**
     * Estás mezclando command con observer y eso hace que el código no se entienda bien, mira como lo tengo yo separado:
     *
     * El command con toda la info para hacer y deshacer:
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/domain/AttackCommand.java
     *
     * EL observer que recibe listeners:
     *
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/infrastructure/analytics/BattleEventNotifier.java
     *
     * Luego lo que hago es añadirle los listeners y notificar cuando me interesa:
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/f9a87c7856a0218e5c6e76532527fb3cb3222a29/src/main/java/com/taller/patrones/application/BattleService.java#L27
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/f9a87c7856a0218e5c6e76532527fb3cb3222a29/src/main/java/com/taller/patrones/application/BattleService.java#L82
     * Hay un método de notificar según lo que pasa (cambio de turno, final de batalla...) y ya los listeners deciden cómo se comportan según eso.
     */
    @Override
    public void execute() {
        observers.forEach(sub -> sub.applyDamageUpdate(battle,attacker, defender, damage, attack));
    }

    @Override
    public void undo() {
        observers.forEach(sub -> sub.unapplyDamageUpdate(battle, attacker, damage));
    }
}
