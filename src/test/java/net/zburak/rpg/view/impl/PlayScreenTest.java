package net.zburak.rpg.view.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import net.zburak.rpg.context.RpgContext;
import net.zburak.rpg.model.Location;
import net.zburak.rpg.model.Npc;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.model.StoryWorld;
import net.zburak.rpg.move.MoveCommandParser;
import net.zburak.rpg.move.impl.MoveUp;
import net.zburak.rpg.view.Screen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by buraq on 28.02.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayScreenTest {

  private RpgContext mockContext;

  @Mock
  private MoveCommandParser mockParser;

  @Mock
  private StoryWorld mockStoryWorld;

  private PlayScreen playScreen;

  @Before
  public void setup(){
    mockContext = Mockito.mock(RpgContext.class);
    MockitoAnnotations.initMocks(this);

    Player mockPlayer = mock(Player.class);
    doReturn(mockPlayer).when(mockContext).getSelectedPlayer();
    doReturn(new Location(1,1)).when(mockPlayer).getCurrentLocation();
    doNothing().when(mockPlayer).setCurrentLocation(any(Location.class));

    Story mockStory = mock(Story.class);
    doReturn(mockStoryWorld).when(mockStory).getWorld();
    doReturn(10).when(mockStoryWorld).getWorldSize();

    MoveUp mockMove = mock(MoveUp.class);
    doReturn(mockMove).when(mockParser).parseCommand(anyString());
    doReturn(new Location(1,2)).when(mockMove).move(any(Location.class));

    doReturn(mockStory).when(mockContext).getStory();

    playScreen = new PlayScreen(mockContext);
    playScreen.setCommandParser(mockParser);
  }

  @Test
  public void getNextScreenIsPlayScreenTest() throws Exception {
    Map<Location, Npc> mockLocationMap = Mockito.mock(HashMap.class);
    doReturn(null).when(mockLocationMap).get(any(Location.class));
    doReturn(mockLocationMap).when(mockStoryWorld).getNpcLocationMap();

    Screen nextScreen = playScreen.getNextScreen("U");

    assertTrue(nextScreen != null);
    assertTrue(nextScreen instanceof PlayScreen);
  }

  @Test
  public void getNextScreenIsFightScreenTest() throws Exception {
    Map<Location, Npc> mockLocationMap = Mockito.mock(HashMap.class);
    doReturn(new Location(1,2)).when(mockLocationMap).get(any(Location.class));
    doReturn(mockLocationMap).when(mockStoryWorld).getNpcLocationMap();

    Screen nextScreen = playScreen.getNextScreen("U");
    assertTrue(nextScreen != null);
    assertTrue(nextScreen instanceof FightScreen);

  }

  @Test
  public void readCommandTest() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("U".getBytes());
    System.setIn(in);
    Screen screen = playScreen.readCommand();
    assertTrue(screen instanceof PlayScreen);
  }

}