package net.zburak.rpg.service.impl;

import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.CharacterStat;
import net.zburak.rpg.model.FightStat;
import net.zburak.rpg.model.Npc;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.service.FightService;
import net.zburak.rpg.view.Screen;
import net.zburak.rpg.view.impl.FightScreen;
import net.zburak.rpg.view.impl.PlayScreen;

/**
 * Created by buraq on 06.03.2017.
 */
public class DefaultFightService implements FightService {

  private RpgContext context;

  public DefaultFightService(RpgContext context) {
    this.context = context;
  }

  /**
   * TODO: implement this method more clean. It is better to display messages on Screen class.
   * Seperating fight operations could be better
   */
  @Override
  public Screen fight(Player player, Npc npc) {

    if (player.getCurrentHealthLevel() > 0 && npc.getCurrentHealthLevel() > 0) {
      CharacterStat playerStat = player.getCharacterStat();
      CharacterStat npcStat = npc.getCharacterStat();
      FightStat npcFightStat = npc.getFightStat();

      int playerAttackPower = calculateAttack(playerStat);
      int npcAttackPower = calculateAttack(npcFightStat, npcStat);

      //First create damage on NPC
      int damageOnNpc = playerAttackPower - npc.getCharacterStat().getDefence();

      if (damageOnNpc > npc.getCurrentHealthLevel()) {
        System.out.println("You killed the " + npc.getName());
        player.getInventory().addAll(npc.getRewardItems());
        npc.getRewardItems().stream().map(i -> "You won " + i.getName())
            .forEach(System.out::println);
        System.out.println("You gain " + npc.getExpReward() + " exp point");

        int newExpLevel =
            playerStat.getCurrentExperienceLevel() + npc.getExpReward();
        if (newExpLevel >= 100) {//Level up!!
          player.setCharacterLevel(player.getCharacterLevel() + 1);
          player.getCharacterStat().setCurrentExperienceLevel(0);
          System.out.println("Level up!! New level is " + player.getCharacterLevel());
        } else {
          player.getCharacterStat().setCurrentExperienceLevel(newExpLevel);
        }

        //Remove npc from map
        context.getStory().getWorld().getNpcLocationMap().remove(player.getCurrentLocation());

        return new PlayScreen(context);

      } else {
        npc.setCurrentHealthLevel(npc.getCurrentHealthLevel() - damageOnNpc);
      }

      //Then create damage on Player if npc still alive
      int damageOnPlayer = npcAttackPower - player.getCharacterStat().getDefence();
      if (damageOnPlayer > player.getCurrentHealthLevel()) {
        System.out.println("YOU LOST!!!");
        System.out.println("See you in another life brotha!!");
        System.exit(1);
      } else {
        player.setCurrentHealthLevel(player.getCurrentHealthLevel() - damageOnPlayer);
        System.out.println("Your health level: " + player.getCurrentHealthLevel());
        System.out.println(npc.getName() + " health level: " + npc.getCurrentHealthLevel());
      }
    }

    //update context
    context.setSelectedPlayer(player);
    context.getStory().getWorld().getNpcLocationMap().put(player.getCurrentLocation(), npc);

    return new FightScreen(context);
  }

  @Override
  public int calculateAttack(FightStat npcFightStat, CharacterStat npcStat) {
    Integer hitPoint = npcFightStat.getHitPoint();
    Integer manaPoint = npcFightStat.getManaPoint() > 0 ? npcFightStat.getManaPoint() : 1;
    Integer attack = npcStat.getAttack();
    int calculatedAttack = (hitPoint + attack) * manaPoint;
    return calculatedAttack;
  }

  @Override
  public int calculateAttack(CharacterStat playerStat) {
    Integer attack = playerStat.getAttack();
    Integer dexterity = playerStat.getDexterity();
    Integer intelligence = playerStat.getIntelligence();
    Integer strength = playerStat.getStrength();
    int calculatedAttack = (attack * strength) + (dexterity * intelligence);
    return calculatedAttack > 0 ? calculatedAttack : 10;
  }

}
