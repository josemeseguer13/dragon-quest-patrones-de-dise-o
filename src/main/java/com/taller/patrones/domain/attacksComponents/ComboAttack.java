package com.taller.patrones.domain.attacksComponents;

/**
 * Está bien, pero en el diagrama del composite, hay también métodos para añadir
 * un nuevo item (ataque), eliminarlo u obtener la lista:
 *
 * https://refactoring.guru/es/design-patterns/composite
 *
 * No es obligatorio que lo hagas así, si por ejemplo quieres limitar que los combos sean algo
 * cerrado. Pero te lo pongo por si acaso no lo añadiste porque no te diste cuenta. Si ha sido a posta, perfecto.
 */

public class ComboAttack implements AttackComponent {

    private AttackComponent[] childrens;

    public ComboAttack(AttackComponent[] childrens) {
        this.childrens = childrens;
    }

    @Override
    public int getBasePower() {
        int total = 0;
        for (AttackComponent attackComponent : childrens){
            total+= attackComponent.getBasePower();
        }
        return total;
    }
}
