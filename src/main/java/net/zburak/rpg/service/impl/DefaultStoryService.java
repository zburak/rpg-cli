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

    //centaur
    Npc centaur = Npc.builder().expReward(20)
        .fightStat(FightStat.builder().hitPoint(50).manaPoint(20).build()).rewardItems(
            Arrays.asList(Item.builder().name("Arch")
                .fightStat(FightStat.builder().manaPoint(2).hitPoint(16).build()).build()))
        .build();
    centaur.setCharacterLevel(5);
    centaur.setCharacterStat(
        CharacterStat.builder().attack(20).currentExperienceLevel(3).defence(25).dexterity(3)
            .intelligence(10).strength(6).build());
    centaur.setCurrentHealthLevel(100);
    centaur.setName("Centaur");
    centaur.setCurrentLocation(new Location(2,2));

    //Cyclops
    Npc cyclops = Npc.builder().expReward(40)
        .fightStat(FightStat.builder().hitPoint(80).manaPoint(1).build()).rewardItems(
            Arrays.asList(Item.builder().name("Helmet")
                .fightStat(FightStat.builder().manaPoint(10).hitPoint(1).build()).build()))
        .build();
    cyclops.setCharacterLevel(9);
    cyclops.setCharacterStat(
        CharacterStat.builder().attack(25).currentExperienceLevel(9).defence(45).dexterity(3)
            .intelligence(2).strength(9).build());
    cyclops.setCurrentHealthLevel(100);
    cyclops.setName("Cyclops");
    cyclops.setCurrentLocation(new Location(1,1));

    //Chimaera
    Npc chimaera = Npc.builder().expReward(60)
        .fightStat(FightStat.builder().hitPoint(60).manaPoint(10).build()).rewardItems(
            Arrays.asList(Item.builder().name("Sword")
                .fightStat(FightStat.builder().manaPoint(1).hitPoint(30).build()).build()))
        .build();
    chimaera.setCharacterLevel(5);
    chimaera.setCharacterStat(
        CharacterStat.builder().attack(20).currentExperienceLevel(3).defence(30).dexterity(3)
            .intelligence(10).strength(6).build());
    chimaera.setCurrentHealthLevel(100);
    chimaera.setName("Chimaera");
    chimaera.setCurrentLocation(new Location(5,9));


    //Hercules
    Player hercules = Player.builder().cash(500).raceName("Human").inventory(new ArrayList<>()).build();
    hercules.setCurrentLocation(new Location(0, 0));
    hercules.setName("Hercules");
    hercules.setCharacterStat(CharacterStat.builder().attack(20).currentExperienceLevel(3).defence(40).dexterity(3)
        .intelligence(10).strength(6).build());
    hercules.setCharacterLevel(1);
    hercules.setCurrentHealthLevel(100);

    //Jason
    Player jason = Player.builder().cash(500).raceName("Human").inventory(new ArrayList<>()).build();
    jason.setCurrentLocation(new Location(0, 0));
    jason.setName("Jason");
    jason.setCharacterStat(CharacterStat.builder().attack(20).currentExperienceLevel(3).defence(20).dexterity(5)
        .intelligence(12).strength(4).build());
    jason.setCharacterLevel(1);
    jason.setCurrentHealthLevel(100);

    Map<Location, Npc> map = new HashMap<>();
    map.put(chimaera.getCurrentLocation(), chimaera);
    map.put(cyclops.getCurrentLocation(), cyclops);
    map.put(centaur.getCurrentLocation(), centaur);

    StoryWorld storyWorld = new StoryWorld(new int[10][10]);
    storyWorld.setNpcLocationMap(map);
    Map<String, Player> availablePlayers = new HashMap<>();
    availablePlayers.put(hercules.getName(), hercules);
    availablePlayers.put(jason.getName(), jason);
    Story story = Story.builder().availableNpcs(Arrays.asList(chimaera, cyclops, centaur)).availablePlayers(availablePlayers)
        .world(storyWorld).build();
    return story;
  }
}
