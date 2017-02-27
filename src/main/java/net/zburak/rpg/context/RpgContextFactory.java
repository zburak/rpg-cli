package net.zburak.rpg.context;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.zburak.rpg.context.impl.RpgContextImpl;
import net.zburak.rpg.service.impl.DefaultStoryService;

/**
 * Created by buraq on 26.02.2017.
 */
@Slf4j
public class RpgContextFactory {

  private static RpgContextFactory instance = new RpgContextFactory();

  @Getter
  private RpgContext rpgContext;

  private RpgContextFactory() {

  }

  public static RpgContextFactory getInstance() {
    return instance;
  }

  public RpgContext createContext(){
    //story service can be selected dynamic
    rpgContext = new RpgContextImpl(new DefaultStoryService());
    log.info("New context is created");
    return rpgContext;
  }

}
