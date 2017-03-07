package net.zburak.rpg.service;

import java.io.Serializable;
import net.zburak.rpg.model.Story;

/**
 * Service for story creation
 *
 * Created by buraq on 26.02.2017.
 */
public interface StoryService extends Serializable {

  /**
   * Generates main story of the game
   *
   * @return
   */
  public Story generateStory();

}
