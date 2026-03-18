package com.taller.patrones.domain.factories;

import com.taller.patrones.domain.attacksComponents.Attack;

/**
 * Bien todo, pero te he cambiado el nombre por facilitar entender qué hace esta interfaz.
 *
 * Ya existe una clase llamada "AttackType" (La tienes dentro de Attack). Si llamas a esta interfaz
 * AttackTypeFActory, parece que la factory es de esa clase concreta, no de todo el Attack.
 *
 * Elegir el nombre correcto hace que tu código sea más entendible y ahorra tiempo a compañeros y a tí en el futuro :)
 */
public interface AttackFactory {
    Attack createAttack();
}
