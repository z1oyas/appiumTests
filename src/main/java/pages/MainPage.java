package pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.elements.AllertPopup;
import jakarta.inject.Inject;
import org.openqa.selenium.By;
import  io.appium.java_client.AppiumBy.ByAndroidUIAutomator;
import pages.elements.ChatElement;

public class MainPage extends ACommon {

  private final AllertPopup allertPopup;
  public final ChatElement chatElement;

  @Inject
  public MainPage(AllertPopup allertPopup,ChatElement chatElement) {
    this.allertPopup = allertPopup;
    this.chatElement = chatElement;
  }

  public SelenideElement mainPageBlock= $(ByAndroidUIAutomator.androidUIAutomator(("new UiSelector().className(\"android.view.ViewGroup\").instance(14)")));
  public ElementsCollection mainPageBlockFolders = mainPageBlock.$$(By.xpath(".//android.view.View/android.view.ViewGroup/android.view.ViewGroup[2]"));
  public ElementsCollection mainPageBlockTitles = mainPageBlock.$$(By.xpath(".//android.view.View/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView"));

  public SelenideElement PremiumButton = $(ByAndroidUIAutomator.androidUIAutomator(("new UiSelector().className(\"android.view.ViewGroup\").instance(33)")));

  public void closeAllertPopup(){
    if(allertPopup.popupBody.isDisplayed()) {
      System.out.println("Close allert popup");
      allertPopup.okButton.shouldBe(enabled).click();
    }
    else System.out.println("No allert popup");
  }
}

