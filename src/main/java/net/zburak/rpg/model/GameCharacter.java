package net.zburak.rpg.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Base type for all game characters
 * Created by buraq on 25.02.2017.
 */
@Getter
@Setter
public abstract class GameCharacter implements Serializable {

  protected String name;

  protected Integer characterLevel;

  protected Integer currentHealthLevel;

  protected CharacterStat characterStat;

  protected Location currentLocation;

}
