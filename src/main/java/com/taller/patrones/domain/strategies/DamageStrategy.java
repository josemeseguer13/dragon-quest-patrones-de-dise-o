package com.taller.patrones.domain.strategies;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Character;

public interface DamageStrategy { //AttackTypeStrategy es un nombre raro... es como una estrategia para saber qué
    // tipo de ataque es. Es mejor este nombre. Nombres de clases y variables -> Documentación gratis. Mejor eso que
    // escribir un documento de docuentación ¿no?
    int calculateDamage(Character attacker, Character defender, Attack attack);
}
