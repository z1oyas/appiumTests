package extensions;

import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.Setup;
import modules.AppiumModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MobileTestExtension implements BeforeEachCallback, AfterEachCallback {
  private static final ThreadLocal<Injector> INJECTOR_THREAD_LOCAL = new ThreadLocal<>();


  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Setup.configure();
    Injector injector = Guice.createInjector(new AppiumModule());
    INJECTOR_THREAD_LOCAL.set(injector);
    injector.injectMembers(extensionContext.getTestInstance().orElseThrow());
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    WebDriverRunner.closeWebDriver();
    INJECTOR_THREAD_LOCAL.remove();
  }
}
