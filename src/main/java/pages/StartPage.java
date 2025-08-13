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

  public final SelenideElement buttonNext = $(By.xpath("//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"));
  public final SelenideElement title1 = $(By.xpath("//android.widget.TextView[@text=\"Chat to improve your English\"]"));
  public final SelenideElement title2 = $(By.xpath("//android.widget.TextView[@text=\"Learn new words and grammar\"]"));
  public final SelenideElement title3 = $(By.xpath("//android.widget.TextView[@text=\"7 days FREE\"]"));
  public final SelenideElement skipButton = $(By.xpath("//android.widget.TextView[@text=\"Skip >\"]"));
  public final SelenideElement buttonPrice = $(By.xpath("//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
  public final ElementsCollection buttonPriceText = buttonPrice.$$(By.xpath(".//android.widget.TextView"));

  public void skipStartPage() {
    buttonNext.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    System.out.println("First screen");
    buttonNext.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    System.out.println("Second screen");
    skipButton.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    System.out.println("Skip button clicked");

  }
}
