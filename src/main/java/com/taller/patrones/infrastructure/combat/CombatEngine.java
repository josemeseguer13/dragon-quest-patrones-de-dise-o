package com.taller.patrones.infrastructure.combat;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.factories.*;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.strategies.DamageStrategy;
import com.taller.patrones.domain.strategies.NormalTypeStrategy;

import java.util.Map;

import static com.taller.patrones.domain.attacksComponents.Attack.AttackType.*;

/**
 * Motor de combate. Calcula daño y crea ataques.
 * <p>
 * Nota: Esta clase crece cada vez que añadimos un ataque nuevo o un tipo de daño distinto.
 */
public class CombatEngine {
    private Map<Attack.AttackType, DamageStrategy> attackFactoryMap = Map.of(
            NORMAL, new NormalTypeStrategy(),
            SPECIAL, new NormalTypeStrategy(),
            STATUS, new NormalTypeStrategy(),
            CRITICO, new NormalTypeStrategy() //Tu AttackType ya es un enumerado, ¿por qué no lo usas aquí?
    );

    /**
     * Cada ataque nuevo requiere modificar este método.
     */
    public Attack createAttack(AttackFactory attackFactory) {
        return attackFactory.createAttack();
    }

    /**
     * Calcula el daño según el tipo de ataque.
     * Cada fórmula nueva (ej. crítico, veneno con tiempo) requiere modificar este switch.
     */
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        DamageStrategy damageStrategy = attackFactoryMap.get(attack.getType());
        return damageStrategy.calculateDamage(attacker, defender, attack); //Bien
    }
}
