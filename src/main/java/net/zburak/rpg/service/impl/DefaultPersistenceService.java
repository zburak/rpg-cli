package net.zburak.rpg.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lombok.extern.slf4j.Slf4j;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.context.RpgContextFactory;
import net.zburak.rpg.service.PersistenceService;

/**
 * Created by buraq on 08.03.2017.
 * TODO: implement unit tests
 */
@Slf4j
public class DefaultPersistenceService implements PersistenceService {

  @Override
  public void saveGame(RpgContext context) {

    File dir = new File("data");
    if (!dir.exists()) {
      dir.mkdir();
    }
    try {
      FileOutputStream fileOutputStream = null;
      fileOutputStream = new FileOutputStream(dir + "/game.dat");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(context);
      System.out.println("Game saved with name data/game.dat");
    } catch (IOException e) {
      log.error("An error occured during save operation ", e);
    }
  }

  @Override
  public RpgContext loadGame() {
    try {
      ObjectInput input = new ObjectInputStream(
          new BufferedInputStream(new FileInputStream("data/game.dat")));
      log.info("Game is loading...");
      return (RpgContext) input.readObject();
    } catch (IOException | ClassNotFoundException e) {
      log.error("An error occured during load operation {}", e.getCause());
    }
    return RpgContextFactory.getInstance().createContext();
  }
}
