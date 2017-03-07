package net.zburak.rpg.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.CharacterStat;
import net.zburak.rpg.model.FightStat;
import net.zburak.rpg.model.Item;
import net.zburak.rpg.model.Location;
import net.zburak.rpg.model.Npc;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.model.StoryWorld;
import net.zburak.rpg.view.Screen;
import net.zburak.rpg.view.impl.FightScreen;
import net.zburak.rpg.view.impl.PlayScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by buraq on 07.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultFightServiceTest {

  private RpgContext mockContext;

  private DefaultFightService fightService;

  @Mock
  private Player mockPlayer;

  @Mock
  private Npc mockNpc;

  @Mock
  private FightStat mockNpcFightStat;

  @Mock
  private CharacterStat mockNpcStat;

  @Mock
  private CharacterStat mockPlayerStat;

  @Spy
  private HashMap<Location, Npc> spyNpcMap;

  @Before
  public void setUp() throws Exception {

    mockContext = Mockito.mock(RpgContext.class);
    MockitoAnnotations.initMocks(this);
    fightService = new DefaultFightService(mockContext);

    doReturn(mockNpcFightStat).when(mockNpc).getFightStat();
    doReturn(10).when(mockNpcFightStat).getHitPoint();
    doReturn(2).when(mockNpcFightStat).getManaPoint();

    doReturn(mockNpcStat).when(mockNpc).getCharacterStat();
    doReturn(20).when(mockNpcStat).getDefence();
    doReturn(20).when(mockNpcStat).getAttack();

    doReturn(mockPlayerStat).when(mockPlayer).getCharacterStat();
    doReturn(5).when(mockPlayerStat).getAttack();
    doReturn(5).when(mockPlayerStat).getDexterity();
    doReturn(5).when(mockPlayerStat).getIntelligence();
    doReturn(5).when(mockPlayerStat).getStrength();
    doReturn(20).when(mockPlayerStat).getDefence();

    doReturn(new ArrayList<Item>()).when(mockPlayer).getInventory();
    doReturn(new ArrayList<Item>()).when(mockNpc).getRewardItems();

    Story mockStory = Mockito.mock(Story.class);
    doReturn(mockStory).when(mockContext).getStory();
    StoryWorld mockWorld = Mockito.mock(StoryWorld.class);
    doReturn(mockWorld).when(mockStory).getWorld();
    doReturn(spyNpcMap).when(mockWorld).getNpcLocationMap();

    doNothing().when(mockContext).setSelectedPlayer(any(Player.class));
    doReturn(new Location(2, 2)).when(mockPlayer).getCurrentLocation();

    doReturn("testname").when(mockNpc).getName();

  }

  @Test
  public void fightTest() throws Exception {
    doReturn(100).when(mockPlayer).getCurrentHealthLevel();
    doReturn(100).when(mockNpc).getCurrentHealthLevel();

//    doReturn(100).when(mockNpc).getExpReward();
//    doReturn(0).when(mockPlayerStat).getCurrentExperienceLevel();
//    doReturn(2).doReturn(3).when(mockPlayer).getCharacterLevel();

//    doNothing().when(mockPlayer).setCharacterLevel(anyInt());
//    doNothing().when(mockPlayerStat).setCurrentExperienceLevel(anyInt());

    Screen fight = fightService.fight(mockPlayer, mockNpc);
    assertTrue(fight instanceof FightScreen);
    verify(mockPlayer, times(1)).setCurrentHealthLevel(anyInt());
    verify(mockPlayer, times(0)).setCharacterLevel(anyInt());
    verify(mockPlayer, times(0)).getInventory();

    verify(mockNpc, times(1)).setCurrentHealthLevel(anyInt());
    verify(mockNpc, times(0)).getRewardItems();
    verify(spyNpcMap, times(0)).remove(any(Location.class));
  }

  @Test
  public void fightKillAndLevelUpTest() throws Exception {
    doReturn(100).when(mockPlayer).getCurrentHealthLevel();
    doReturn(20).when(mockNpc).getCurrentHealthLevel();

    doReturn(100).when(mockNpc).getExpReward();
    doReturn(0).when(mockPlayerStat).getCurrentExperienceLevel();
    doReturn(2).doReturn(3).when(mockPlayer).getCharacterLevel();

    doNothing().when(mockPlayer).setCharacterLevel(anyInt());
    doNothing().when(mockPlayerStat).setCurrentExperienceLevel(anyInt());

    Screen fight = fightService.fight(mockPlayer, mockNpc);
    assertTrue(fight instanceof PlayScreen);
    verify(mockPlayer, times(0)).setCurrentHealthLevel(anyInt());
    verify(mockPlayer, times(1)).setCharacterLevel(anyInt());
    verify(mockPlayer, times(1)).getInventory();

    verify(mockNpc, times(0)).setCurrentHealthLevel(anyInt());
    verify(mockNpc, times(2)).getRewardItems();
    verify(spyNpcMap, times(1)).remove(any(Location.class));
  }


  @Test
  public void calculateAttack() throws Exception {
    int attack = fightService.calculateAttack(mockPlayerStat);
    assertTrue(attack > 0);
    assertTrue(attack == mockPlayerStat.getAttack() * mockPlayerStat.getStrength()
        + mockPlayerStat.getDexterity() * mockPlayerStat.getIntelligence());

  }

  @Test
  public void calculateAttack1() throws Exception {
    int attack = fightService.calculateAttack(mockNpcFightStat, mockNpcStat);
    assertTrue(attack > 0);
    assertTrue(attack == (mockNpcFightStat.getHitPoint() + mockNpcStat.getAttack()) * mockNpcFightStat.getManaPoint());
  }

}