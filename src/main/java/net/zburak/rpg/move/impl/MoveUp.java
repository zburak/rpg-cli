package net.zburak.rpg.move.impl;

import net.zburak.rpg.model.Location;
import net.zburak.rpg.move.Move;

/**
 * Created by buraq on 28.02.2017.
 */
public class MoveUp implements Move {

  private int maxLength;

  public MoveUp(int maxLength) {
    this.maxLength = maxLength;
  }

  @Override
  public Location move(Location currentLocation) {
    int y = currentLocation.getY();
    if (y + 1 > maxLength) {
      System.out.println("Invalid Location!!\n");
      return currentLocation;
    }
    return new Location(currentLocation.getX(), y + 1);
  }
}
