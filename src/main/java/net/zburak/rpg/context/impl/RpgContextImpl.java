package net.zburak.rpg.context.impl;

import lombok.Getter;
import lombok.Setter;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.service.StoryService;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 26.02.2017.
 */
public class RpgContextImpl implements RpgContext {

  @Setter
  private StoryService storyService;

  @Setter
  @Getter
  private Player selectedPlayer;

  @Getter
  @Setter
  private Screen lastScreen;

  @Getter
  private Story story;

  public RpgContextImpl(StoryService storyService) {

    this.storyService = storyService;
    story = storyService.generateStory();
  }

}
