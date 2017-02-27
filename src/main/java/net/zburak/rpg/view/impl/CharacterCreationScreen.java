package net.zburak.rpg.view.impl;

import java.util.List;
import java.util.stream.Collectors;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.view.AbstractScreen;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 26.02.2017.
 */
public class CharacterCreationScreen extends AbstractScreen {

  private RpgContext rpgContext;

  public CharacterCreationScreen() {
    super();
  }

  public CharacterCreationScreen(RpgContext rpgContext) {
    super();
    this.rpgContext = rpgContext;
  }

  @Override
  protected Screen getNextScreen(String command) {
    rpgContext.setSelectedPlayer(rpgContext.getStory().getAvailablePlayers().get(command));
    return new PlayScreen();
  }

  protected List<String> getCommandList() {

    return getAvailableCharacters();
  }

  protected void printOptions() {
    System.out.println("Select your character");
    getAvailableCharacters().stream().forEach(System.out::println);
  }

  private List<String> getAvailableCharacters() {
    Story story = rpgContext.getStory();
    return story.getAvailablePlayers().keySet().stream().collect(Collectors.toList());
  }
}
