package net.zburak.rpg.view.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.model.StoryWorld;
import net.zburak.rpg.view.Screen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by buraq on 27.02.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterCreationScreenTest {

  @Mock
  private RpgContext rpgContext;

  @Mock
  Player mockPlayer;

  @InjectMocks
  private CharacterCreationScreen characterCreationScreen;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    Story mockStory = Mockito.mock(Story.class);
    doReturn(mockStory).when(rpgContext).getStory();
    Map<String, Player> playerMap = new HashMap<>();
    playerMap.put("testName", mockPlayer);
    doReturn(playerMap).when(mockStory).getAvailablePlayers();
    doReturn(mockPlayer).when(rpgContext).getSelectedPlayer();
    StoryWorld mockWorld = Mockito.mock(StoryWorld.class);
    doReturn(mockWorld).when(mockStory).getWorld();
    doReturn(10).when(mockWorld).getWorldSize();
  }

  @Test
  public void getNextScreenTest() throws Exception {
    Screen s = characterCreationScreen.getNextScreen("testName");
    assertTrue(s instanceof PlayScreen);
    assertTrue(rpgContext.getSelectedPlayer().equals(mockPlayer));
  }

  @Test
  public void getCommandListTest() throws Exception {

    List<String> commandList = characterCreationScreen.getCommandList();
    assertTrue(commandList.contains("testName"));
  }

  @Test
  public void readCommandTest() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("testName".getBytes());
    System.setIn(in);
    Screen screen = characterCreationScreen.readCommand();
    assertTrue(screen instanceof PlayScreen);

  }

}