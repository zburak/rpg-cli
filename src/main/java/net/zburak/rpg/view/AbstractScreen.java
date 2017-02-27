package net.zburak.rpg.view;

import java.util.List;
import java.util.Scanner;

/**
 * Created by buraq on 26.02.2017.
 */
public abstract class AbstractScreen implements Screen {

  protected Scanner scanner;

  public AbstractScreen() {

  }

  public void showScreen() {
    printOptions();
  }

  public Screen readCommand() {
    String command = null;
    do {
      command = getInput();
      command = command.trim();
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

  private String getInput(){
    if (scanner == null) {
      scanner = new Scanner(System.in);
    }
    return scanner.next();
  }


}
