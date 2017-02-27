package net.zburak.rpg.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * Created by buraq on 25.02.2017.
 */
@Data
@Builder
public class CharacterStat implements Serializable {

  private Integer strength;
  private Integer intelligence;
  private Integer dexterity;
  private Integer defence;
  private Integer attack;
  private Integer currentExperienceLevel;

}
