package net.zburak.rpg.view.impl;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.Location;
import net.zburak.rpg.move.MoveCommandParser;
import net.zburak.rpg.view.AbstractScreen;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 27.02.2017.
 */
@Slf4j
public class PlayScreen extends AbstractScreen {

  @Setter
  private MoveCommandParser commandParser;

  public PlayScreen(RpgContext rpgContext) {
    this.rpgContext = rpgContext;
    this.commandParser = new MoveCommandParser(rpgContext);
  }


  @Override
  protected Screen getNextScreen(String command) {
    Location newLocation = commandParser.parseCommand(command)
        .move(rpgContext.getSelectedPlayer().getCurrentLocation());
    log.info("Player Location {} {}", newLocation.getX(), newLocation.getY());
    rpgContext.getSelectedPlayer().setCurrentLocation(newLocation);

    if(rpgContext.getStory().getWorld().getNpcLocationMap().get(newLocation) != null){
      return new FightScreen(rpgContext);
    }
    return this;
  }

  @Override
  protected List<String> getCommandList() {
    return Arrays.asList("U", "D", "R", "L");
  }

  @Override
  protected void printOptions() {
    System.out.println("U- Move Up");
    System.out.println("D- Move Down");
    System.out.println("R- Move Right");
    System.out.println("L- Move Left");
  }
}
