package net.zburak.rpg.manager;

import net.zburak.rpg.context.RpgContextFactory;
import net.zburak.rpg.view.Screen;
import net.zburak.rpg.view.impl.IntroScreen;

/**
 * Created by buraq on 26.02.2017.
 */
public class GameManager {

  public static void run() {
    RpgContextFactory.getInstance().createContext();
    Screen intro = new IntroScreen();
    intro.showScreen();
    Screen screen = intro.readCommand();
    while (screen != null) {
      screen.showScreen();
      screen = screen.readCommand();
    }
  }
}
