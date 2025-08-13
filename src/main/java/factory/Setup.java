package factory;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class Setup {
  private static DesiredCapabilities capabilities;

  public static void configure() {
    Configuration.timeout = 15000;
    capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", Platform.ANDROID);
    capabilities.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);
    capabilities.setCapability("appium:deviceName", System.getProperty("deviceName"));
    capabilities.setCapability("appium:appPackage", System.getProperty("appPackage"));
    capabilities.setCapability("appium:appActivity", System.getProperty("appActivity"));
    capabilities.setCapability("appium:noReset", System.getProperty("noReset"));

    URL appiumServerUrl = null;
    try {
      appiumServerUrl = new URL(System.getProperty("base.url"));
    }catch (MalformedURLException e){
      System.out.println("Bad appium server url: \n" + e.getMessage());
    }


    AppiumDriver driver = new AndroidDriver(appiumServerUrl, capabilities);

    WebDriverRunner.setWebDriver(driver);
  }
}
