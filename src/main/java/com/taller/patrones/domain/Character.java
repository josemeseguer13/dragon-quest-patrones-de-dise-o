package com.taller.patrones.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Representa un personaje en combate.
 */
@Getter
@Builder //Sería más bien esta anotación para crearte el builder, aunque en esta ocasión esperaba que lo hiciérais a mano
//Mira cómo lo hice yo: https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/f9a87c7856a0218e5c6e76532527fb3cb3222a29/src/main/java/com/taller/patrones/domain/model/Character.java#L74
public class Character {

    private final String name;
    private int currentHp;
    private final int maxHp;
    private final int attack;
    private final int defense;
    private final int speed;

    @Builder
    public Character(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, currentHp - damage);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public double getHpPercentage() {
        return maxHp > 0 ? (double) currentHp / maxHp * 100 : 0;
    }
}
