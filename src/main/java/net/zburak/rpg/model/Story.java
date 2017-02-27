package net.zburak.rpg.model;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Created by buraq on 26.02.2017.
 */
@Data
@Builder
public class Story implements Serializable {

  private StoryWorld world;
  private List<Player> availablePlayers;
  private List<Npc> availableNpcs;

}
