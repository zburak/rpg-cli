package net.zburak.rpg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.zburak.rpg.model.CharacterStat;
import net.zburak.rpg.model.FightStat;
import net.zburak.rpg.model.Item;
import net.zburak.rpg.model.Location;
import net.zburak.rpg.model.Npc;
import net.zburak.rpg.model.Player;
import net.zburak.rpg.model.Story;
import net.zburak.rpg.model.StoryWorld;
import net.zburak.rpg.service.StoryService;

/**
 * Creates the story with default scenario and characters
 * StoryService interface can be implemented for reading file, data source or any other
 * story creation option
 * Created by buraq on 26.02.2017.
 */
public class DefaultStoryService implements StoryService {

  public Story generateStory() {
    Npc monster = Npc.builder().expReward(20)
        .fightStat(FightStat.builder().hitPoint(50).manaPoint(20).build()).rewardItems(
            Arrays.asList(Item.builder().name("Sword")
                .fightStat(FightStat.builder().manaPoint(10).hitPoint(16).build()).build()))
        .build();
    monster.setCharacterLevel(5);
    monster.setCharacterStat(
        CharacterStat.builder().attack(20).currentExperienceLevel(3).defence(5).dexterity(3)
            .intelligence(10).strength(6).build());
    monster.setCurrentHealthLevel(100);
    monster.setName("hede");
    monster.setCurrentLocation(new Location(2,2));

    Player humanPlayer = Player.builder().cash(500).raceName("Human").inventory(new ArrayList<>()).build();
    humanPlayer.setCurrentLocation(new Location(0, 0));
    humanPlayer.setName("Burak");
    humanPlayer.setCharacterStat(CharacterStat.builder().attack(20).currentExperienceLevel(3).defence(5).dexterity(3)
        .intelligence(10).strength(6).build());
    humanPlayer.setCharacterLevel(1);
    humanPlayer.setCurrentHealthLevel(100);

    Map<Location, Npc> map = new HashMap<>();
    map.put(monster.getCurrentLocation(), monster);

    StoryWorld storyWorld = new StoryWorld(new int[10][10]);
    storyWorld.setNpcLocationMap(map);
    Map<String, Player> availablePlayers = new HashMap<>();
    availablePlayers.put(humanPlayer.getName(), humanPlayer);
    Story story = Story.builder().availableNpcs(Arrays.asList(monster)).availablePlayers(availablePlayers)
        .world(storyWorld).build();
    return story;
  }
}
