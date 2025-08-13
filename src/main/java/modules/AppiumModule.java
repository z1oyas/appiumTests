package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import pages.elements.AllertPopup;
import pages.StartPage;
import pages.elements.ChatElement;

public class AppiumModule extends AbstractModule {

  @Provides
  @Singleton
  public StartPage getStartPage() {
    return new StartPage();
  }


  @Provides
  @Singleton
  public AllertPopup getAllertPopup() {
    return new AllertPopup();
  }

  @Provides
  @Singleton
  public ChatElement getChatElement() {
    return new ChatElement();
  }
}
