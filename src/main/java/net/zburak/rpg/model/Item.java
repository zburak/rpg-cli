package net.zburak.rpg.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * Created by buraq on 25.02.2017.
 */
@Data
@Builder
public class Item implements Serializable {

  private String name;
  private FightStat fightStat;
}
