package net.zburak.rpg.view;

import java.util.List;
import java.util.Scanner;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.service.PersistenceService;
import net.zburak.rpg.service.impl.DefaultPersistenceService;
import net.zburak.rpg.view.impl.IntroScreen;

/**
 * Created by buraq on 26.02.2017.
 */
public abstract class AbstractScreen implements Screen {

  protected transient Scanner scanner;

  protected RpgContext rpgContext;

  private PersistenceService persistenceService;

  private final static String SAVE = "S";

  public void showScreen() {
    if(rpgContext != null){
      rpgContext.setLastScreen(this);
    }
    printOptions();
  }

  public Screen readCommand() {
    String command = null;
    do {
      command = getInput();
      command = command.trim();

      if (SAVE.equals(command)) {
        persistenceService = new DefaultPersistenceService();
        persistenceService.saveGame(rpgContext);
        return new IntroScreen();
      }

      if (command != null && !command.equals("") && !getCommandList().contains(command)) {
        System.out.println("Please enter a valid command");
      } else {
        return getNextScreen(command);
      }
    } while (true);
  }

  protected abstract Screen getNextScreen(String command);

  protected abstract List<String> getCommandList();

  protected abstract void printOptions();

  private String getInput() {
    if (scanner == null) {
      scanner = new Scanner(System.in);
    }
    return scanner.next();
  }

}
