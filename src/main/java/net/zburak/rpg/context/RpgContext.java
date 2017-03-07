package net.zburak.rpg.context;

import java.io.Serializable;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.service.StoryService;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 26.02.2017.
 */
public interface RpgContext extends Serializable {

  void setStoryService(StoryService storyService);

  Story getStory();

  void setSelectedPlayer(Player player);

  Player getSelectedPlayer();

  Screen getLastScreen();

  void setLastScreen(Screen screen);





}
