package net.zburak.rpg.view.impl;

import java.util.List;
import net.zburak.rpg.view.AbstractScreen;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 27.02.2017.
 */
public class PlayScreen extends AbstractScreen {

  public PlayScreen() {
    super();
  }

  @Override
  protected Screen getNextScreen(String command) {
    return null;
  }

  @Override
  protected List<String> getCommandList() {
    return null;
  }

  @Override
  protected void printOptions() {

  }
}
