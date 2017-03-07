package net.zburak.rpg.service;

import net.zburak.rpg.model.CharacterStat;
import net.zburak.rpg.model.FightStat;
import net.zburak.rpg.model.Npc;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.view.Screen;

/**
 * Manages the fight operations
 *
 * Created by buraq on 06.03.2017.
 */
public interface FightService {

  Screen fight(Player player, Npc npc);

  int calculateAttack(FightStat npcFightStat, CharacterStat npcStat);

  int calculateAttack(CharacterStat playerStat);

}
