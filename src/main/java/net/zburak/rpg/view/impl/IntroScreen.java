package net.zburak.rpg.view.impl;

import java.util.Arrays;
import java.util.List;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.context.RpgContextFactory;
import net.zburak.rpg.service.PersistenceService;
import net.zburak.rpg.service.impl.DefaultPersistenceService;
import net.zburak.rpg.view.AbstractScreen;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 25.02.2017.
 */
public class IntroScreen extends AbstractScreen {

  private static final String BEGIN = "B";
  private static final String LOAD = "L";
  private static final String EXIT = "E";

  @Override
  protected Screen getNextScreen(String command) {
    if (BEGIN.equals(command)) {
      return new CharacterCreationScreen(RpgContextFactory.getInstance().getRpgContext());
    } else if (LOAD.equals(command)) {
      PersistenceService persistenceService = new DefaultPersistenceService();
      RpgContext context = persistenceService.loadGame();
      rpgContext = context;
      return rpgContext.getLastScreen();
    } else if (EXIT.equals(command)) {
      System.out.println("Bye!!");
      System.exit(0);
    }
    return this;
  }

  protected List<String> getCommandList() {
    return Arrays.asList(BEGIN, LOAD, EXIT);
  }

  protected void printOptions() {
    System.out.println("B - Begin");
    System.out.println("L - Load");
    System.out.println("E - Exit");
  }
}
