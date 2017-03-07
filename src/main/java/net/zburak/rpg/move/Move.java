package net.zburak.rpg.move;

import java.io.Serializable;
import net.zburak.rpg.model.Location;

/**
 * Created by buraq on 28.02.2017.
 */
public interface Move extends Serializable{

  Location move(Location currenLocation);

}
