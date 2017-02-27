package net.zburak.rpg.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * Created by buraq on 26.02.2017.
 */
@Data
@Builder
public class Story implements Serializable {

  private StoryWorld world;
  private Map<String, Player> availablePlayers;
  private List<Npc> availableNpcs;

}
