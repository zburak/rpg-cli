package net.zburak.rpg;

import lombok.extern.slf4j.Slf4j;
import net.zburak.rpg.manager.GameManager;

/**
 * Created by buraq on 25.02.2017.
 */
@Slf4j
public class Main {

  public static void main(String args[]) {
    log.info("RPG-CLI started");
    GameManager.run();
  }

}
