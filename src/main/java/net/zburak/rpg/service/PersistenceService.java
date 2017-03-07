package net.zburak.rpg.service;

import java.io.Serializable;
import net.zburak.rpg.context.RpgContext;

/**
 * Created by buraq on 08.03.2017.
 */
public interface PersistenceService extends Serializable {

  void saveGame(RpgContext context);

  RpgContext loadGame();

}
