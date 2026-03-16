package com.taller.patrones.infrastructure.combat;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.factories.*;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.strategies.AttackTypeStrategy;
import com.taller.patrones.domain.strategies.NormalTypeStrategy;

import java.util.Map;

/**
 * Motor de combate. Calcula daño y crea ataques.
 * <p>
 * Nota: Esta clase crece cada vez que añadimos un ataque nuevo o un tipo de daño distinto.
 */
public class CombatEngine {
    private Map<String, AttackTypeStrategy> attackFactoryMap = Map.of(
            "NORMAL", new NormalTypeStrategy(),
            "SPECIAL", new NormalTypeStrategy(),
            "STATUS", new NormalTypeStrategy(),
            "CRITICO", new NormalTypeStrategy()
    );

    /**
     * Crea un ataque a partir de su nombre.
     * Cada ataque nuevo requiere modificar este método.
     */
    public Attack createAttack(AttackTypeFactory attackTypeFactory) {
        return attackTypeFactory.createAttack();
    }

    /**
     * Calcula el daño según el tipo de ataque.
     * Cada fórmula nueva (ej. crítico, veneno con tiempo) requiere modificar este switch.
     */
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        AttackTypeStrategy attackTypeStrategy = attackFactoryMap.get(attack.getType().name());
        return attackTypeStrategy.calculateDamage(attacker, defender, attack);
    }
}
