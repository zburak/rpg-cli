package net.zburak.rpg.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Created by buraq on 25.02.2017.
 */
@Data
@Builder
public class Player extends GameCharacter {

  private List<Item> inventory;
  private int cash;
  private String raceName;
}
