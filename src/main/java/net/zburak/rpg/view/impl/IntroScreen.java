package net.zburak.rpg.view.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.zburak.rpg.context.RpgContextFactory;
import net.zburak.rpg.view.AbstractScreen;
import net.zburak.rpg.view.Screen;

/**
 *
 * Created by buraq on 25.02.2017.
 */
public class IntroScreen extends AbstractScreen {

  public IntroScreen() {
    super();
  }

  @Override
  protected Screen getNextScreen(String command) {
    if("S".equals(command)){
      return new CharacterCreationScreen(RpgContextFactory.getInstance().getRpgContext());
    } else{
      return null;
    }
  }

  protected List<String> getCommandList() {
    return Arrays.asList("S", "L", "E");
  }

  protected void printOptions() {
    System.out.println("S - Start");
    System.out.println("L - Load");
    System.out.println("E - Exit");
  }
}
