package com.taller.patrones.application.subscribers;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;

/**
 * ¿Por qué has decidido hacer un observer para aplicar el daño? Este sería un approach
 * para una aquitectura basada en eventos. Esta que estamos haciendo es más simple.
 *
 * Creo que esto es caer en sobrediseño, ya que le estás dando una vuelta extra a lo que tenemos.
 * Esta lógica puede ir bien en el BattleService o la fachada, no es necesario aplicarle una capa más
 * asignándole un observer. Si quieres, revisa mi código y piensa qué solución te parece más sencilla:
 *
 *
 */
public class StatsUpdater implements Subscriber { // No es UpdatOr, es UpdatEr

    /**
     * Este método tiene muchas responsabilidades, si te das cuenta, no sólo hace un apply del damage como pone
     * el método. En mi código también lo tengo todo en un método, pero el mío tiene un nombre más general:
     *
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/f9a87c7856a0218e5c6e76532527fb3cb3222a29/src/main/java/com/taller/patrones/domain/AttackCommand.java#L40
     */
    public void applyDamageUpdate(Battle battle, Character attacker, Character defender, int damage, Attack attack){
        defender.takeDamage(damage); // 1. Calcula daño
        String target = defender == battle.getPlayer() ? "player" : "enemy";
        battle.setLastDamage(damage, target);
        battle.switchTurn(); // 2. Cambia turno
        if (!defender.isAlive()) { // 3. Mira a ver si termina la batalla
            battle.finish(attacker.getName());
        }
    }

    public void unapplyDamageUpdate(Battle battle, Character defender, int damage){
        defender.takeDamage(-damage);
        battle.switchTurn();
    }
}
