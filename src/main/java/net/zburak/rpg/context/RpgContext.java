package net.zburak.rpg.context;

import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.service.StoryService;

/**
 * Created by buraq on 26.02.2017.
 */
public interface RpgContext {

  public void setStoryService(StoryService storyService);

  public Story getStory();

  public void setSelectedPlayer(Player player);

  public Player getSelectedPlayer();

}
