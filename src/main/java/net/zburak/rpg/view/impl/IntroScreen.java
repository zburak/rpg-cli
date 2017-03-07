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

  private static final String BEGIN = "B";

  @Override
  protected Screen getNextScreen(String command) {
    if(BEGIN.equals(command)){
      return new CharacterCreationScreen(RpgContextFactory.getInstance().getRpgContext());
    } else{
      return null;
    }
  }

  protected List<String> getCommandList() {
    return Arrays.asList("B", "L", "E");
  }

  protected void printOptions() {
    System.out.println("B - Begin");
    System.out.println("L - Load");
    System.out.println("E - Exit");
  }
}
