package net.zburak.rpg.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Represents non player caharacters
 *
 * Created by buraq on 25.02.2017.
 */
@Data
@Builder
public class Npc extends GameCharacter {

  private FightStat fightStat;
  private Integer expReward;
  private List<Item> rewardItems;
}
