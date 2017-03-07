package net.zburak.rpg.move;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.move.impl.MoveDown;
import net.zburak.rpg.move.impl.MoveLeft;
import net.zburak.rpg.move.impl.MoveRight;
import net.zburak.rpg.move.impl.MoveUp;

/**
 * Created by buraq on 28.02.2017.
 */
public class MoveCommandParser implements Serializable {

  private Map<String, Move> moveMap;

  private RpgContext context;

  public MoveCommandParser(RpgContext context) {
    this.context = context;
    int worldSize = context.getStory().getWorld().getWorldSize();
    moveMap = new HashMap<>();
    moveMap.put("U", new MoveUp(worldSize));
    moveMap.put("D", new MoveDown(worldSize));
    moveMap.put("R", new MoveRight(worldSize));
    moveMap.put("L", new MoveLeft(worldSize));
  }

  public Move parseCommand(String moveCommand){
    return moveMap.get(moveCommand);
  }
}
