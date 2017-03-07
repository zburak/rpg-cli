package net.zburak.rpg.view;

import java.io.Serializable;

/**
 * Game control screens interface
 *
 * Created by buraq on 25.02.2017.
 */
public interface Screen extends Serializable {

  /**
   * Displays the screen
   */
  void showScreen();

  /**
   * Reads command from the screen
   */
  Screen readCommand();
}
