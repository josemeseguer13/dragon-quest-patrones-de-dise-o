package com.taller.patrones.application;

import com.taller.patrones.domain.attacksComponents.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import com.taller.patrones.application.commands.ApplyDamageCommand;
import com.taller.patrones.application.commands.Command;
import com.taller.patrones.domain.factories.*;
import com.taller.patrones.application.subscribers.Subscriber;
import com.taller.patrones.infrastructure.combat.CombatEngine;
import com.taller.patrones.infrastructure.persistence.BattleRepository;

import java.util.*;

/**
 * Caso de uso: gestionar batallas.
 * <p>
 * Nota: Crea sus propias dependencias con new. Cada vez que necesitamos
 * un CombatEngine o BattleRepository, hacemos new aquí.
 */
public class BattleService {
    private final Stack<Command> commandHistory = new Stack<>();
    private List<Subscriber> observers = new ArrayList<>();
    private final CombatEngine combatEngine = new CombatEngine();
    private final BattleRepository battleRepository = BattleRepository.getInstance();

    public static final List<String> PLAYER_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL", "ICE_BEAM", "POISON_STING", "THUNDER");
    public static final List<String> ENEMY_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL");

    private Map<String, AttackFactory> attackFactoryMap = Map.of(
            "FIREBALL", new FireballAttackFactory(),
            "ICE BEAM", new IceBeamAttackFactory(),
            "POISON STING", new PoisonStingAttackFactory(),
            "SLASH", new SlashAttackFactory(),
            "TACKLE", new TackleAttackFactory(),
            "THUNDER", new ThunderAttackFactory(),
            "METEORO", new MeteorAttackFactory()
    );

    public BattleStartResult startBattle(String playerName, String enemyName) {
        Character player = Character.builder().name(playerName != null ? playerName : "Héroe").maxHp(150).attack(25).defense(15).speed(20).build();
        Character enemy = Character.builder().name(playerName != null ? playerName : "Dragón").maxHp(120).attack(30).defense(10).speed(15).build();

        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);

        return new BattleStartResult(battleId, battle);
    }

    public Battle getBattle(String battleId) {
        return battleRepository.findById(battleId);
    }

    public void executePlayerAttackFacade(String battleId, String attackName) {
        executePlayerAttack(battleId, attackFactoryMap.getOrDefault(attackName, new TackleAttackFactory()));
    }

    public void executePlayerAttack(String battleId, AttackFactory attackFactory) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || !battle.isPlayerTurn()) return;

        Attack attack = combatEngine.createAttack(attackFactory);
        int damage = combatEngine.calculateDamage(battle.getPlayer(), battle.getEnemy(), attack);
        applyDamage(battle, battle.getPlayer(), battle.getEnemy(), damage, attack);
    }

    public void executeEnemyAttackFacade(String battleId, String attackName) { //? Una Facade sería una clase nueva: https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/interfaces/CombatFacade.java
        executeEnemyAttack(battleId, attackFactoryMap.getOrDefault(attackName, new TackleAttackFactory()));
    }

    public void executeEnemyAttack(String battleId, AttackFactory attackFactory) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || battle.isPlayerTurn()) return;

        Attack attack = combatEngine.createAttack(attackFactory);
        int damage = combatEngine.calculateDamage(battle.getEnemy(), battle.getPlayer(), attack);
        applyDamage(battle, battle.getEnemy(), battle.getPlayer(), damage, attack);
    }
    public void subscribe(Subscriber suscriber) {
        observers.add(suscriber);
    } //Me duele ver estos dos métodos aquí, esto iría en un notifier, una clase aparte: https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/infrastructure/analytics/BattleEventNotifier.java
    public void unsubscribe(Subscriber suscriber) {
        observers.remove(suscriber);
    }

    private void applyDamage(Battle battle, Character attacker, Character defender, int damage, Attack attack) {
        ApplyDamageCommand attackCommand = ApplyDamageCommand.builder().battle(battle).attacker(attacker).defender(defender).damage(damage).attack(attack).observers(observers).build();
        attackCommand.execute();
        commandHistory.push(attackCommand);
    }
    private void undoDamage(){
        Command lastCommand = commandHistory.pop();
        lastCommand.undo();
    }

    public BattleStartResult startBattleFromExternal(Character player, Character enemy) {
        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);
        return new BattleStartResult(battleId, battle);
    }

    public record BattleStartResult(String battleId, Battle battle) {}
}
