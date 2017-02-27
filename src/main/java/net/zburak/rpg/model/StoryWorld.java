package net.zburak.rpg.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by buraq on 26.02.2017.
 */
@Getter
public class StoryWorld implements Serializable{

  private int[][] worldMap;

  @Setter
  private Map<Location, Npc> npcLocationMap;

  public StoryWorld(int[][] world){
    this.worldMap = world;
  }

  public int getWorldSize(){
    return worldMap.length;
  }

}
