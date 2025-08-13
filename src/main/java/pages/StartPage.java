package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import jakarta.inject.Inject;
import org.openqa.selenium.By;

public class StartPage extends ACommon {

  @Inject
  MainPage mainPage;

  public SelenideElement buttonNext = $(By.xpath("//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"));
  public SelenideElement title1 = $(By.xpath("//android.widget.TextView[@text=\"Chat to improve your English\"]"));
  public SelenideElement title2 = $(By.xpath("//android.widget.TextView[@text=\"Learn new words and grammar\"]"));
  public SelenideElement title3 = $(By.xpath("//android.widget.TextView[@text=\"7 days FREE\"]"));
  public SelenideElement skipButton = $(By.xpath("//android.widget.TextView[@text=\"Skip >\"]"));
  public SelenideElement buttonPrice = $(By.xpath("//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
  public ElementsCollection buttonPriceText = buttonPrice.$$(By.xpath(".//android.widget.TextView"));

  public void skipStartPage() {
    buttonNext.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    buttonNext.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    skipButton.shouldBe(visible)
        .shouldBe(enabled)
        .click();
  }
}
