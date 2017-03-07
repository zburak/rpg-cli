package net.zburak.rpg.view.impl;

import java.util.Arrays;
import java.util.List;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.Location;
import net.zburak.rpg.model.Npc;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.service.FightService;
import net.zburak.rpg.service.impl.DefaultFightService;
import net.zburak.rpg.view.AbstractScreen;
import net.zburak.rpg.view.Screen;

/**
 * Created by buraq on 27.02.2017.
 */
public class FightScreen extends AbstractScreen {

  private FightService fightService;

  private static final String TELEPORT = "T";

  public FightScreen(RpgContext rpgContext) {
    this.rpgContext = rpgContext;
    fightService = getFightService();
  }

  @Override
  protected Screen getNextScreen(String command) {

    if (TELEPORT.equals(command)) {
      rpgContext.getSelectedPlayer().setCurrentLocation(new Location(0, 0));
      System.out.println("Teleported to the start point");
      return new PlayScreen(rpgContext);
    } else {

      Player player = rpgContext.getSelectedPlayer();
      Npc npc = rpgContext.getStory().getWorld().getNpcLocationMap()
          .get(player.getCurrentLocation());
      return fightService.fight(player, npc);
    }
  }

  @Override
  protected List<String> getCommandList() {
    return Arrays.asList("F", "T");
  }

  @Override
  protected void printOptions() {
    System.out.println("Monster!!! Choose your destiny!!\n\n");
    System.out.println("F. Fight!!");
    System.out.println("T. Teleport");
    System.out.println("S. Save");
  }

  public FightService getFightService() {
    if (fightService == null) {
      return new DefaultFightService(rpgContext);
    }
    return fightService;
  }
}
