package com.taller.patrones.infrastructure.persistence;

import com.taller.patrones.domain.Battle;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Almacena las batallas activas en memoria.
 * <p>
 * Nota: BattleService hace new BattleRepository() cada vez. Si otro servicio
 * también creara su propio BattleRepository, ¿compartirían las batallas?
 */
public class BattleRepository {

    private static final Map<String, Battle> battles = new ConcurrentHashMap<>(); //Los parámetros de la clase al inicio del todo, por favor

    @Getter //A ver... si, se podría hacer, pero en un singleton se suele esperar un getInstance... Tendría que pensar fuerte si esto es equivalente.
    //Anyway, Lombok mola, yo lo uso mucho, pero recuerda que lo carga el diablo y que a veces hay inconsistencias con anotaciones de spring
    //(para que lo tengas en cuenta en el futuro).
    private static BattleRepository instance = new BattleRepository();

    private BattleRepository() {
    } //constructor privado, bien

    public void save(String id, Battle battle) {
        battles.put(id, battle);
    }

    public Battle findById(String id) {
        return battles.get(id);
    }

    public void remove(String id) {
        battles.remove(id);
    }
}
