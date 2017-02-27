package net.zburak.rpg.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by buraq on 27.02.2017.
 */
@Data
@AllArgsConstructor
public class Location implements Serializable {

  private int x;
  private int y;

}
