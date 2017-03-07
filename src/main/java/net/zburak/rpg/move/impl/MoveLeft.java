package net.zburak.rpg.move.impl;

import net.zburak.rpg.model.Location;
import net.zburak.rpg.move.Move;

/**
 * Created by buraq on 28.02.2017.
 */
public class MoveLeft implements Move {

  private int maxLength;

  public MoveLeft(int maxLength) {
    this.maxLength = maxLength;
  }

  @Override
  public Location move(Location currentLocation) {
    int x = currentLocation.getY();
    if (x - 1 > maxLength || x < 0) {
      System.out.println("Invalid Location!!\n");
      return currentLocation;
    }
    return new Location(x - 1, currentLocation.getY());

  }
}
