package net.zburak.rpg.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * Created by buraq on 25.02.2017.
 */
@Data
@Builder
public class FightStat implements Serializable {

  private Integer hitPoint;
  private Integer manaPoint;

}
