package com.taller.patrones.domain.attacksComponents;

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
