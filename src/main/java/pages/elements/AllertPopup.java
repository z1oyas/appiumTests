package pages.elements;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class AllertPopup {

  public SelenideElement popupBody = $(By.xpath("/hierarchy/android.widget.FrameLayout"));
  public ElementsCollection popupText = popupBody.$$(By.xpath(".//android.widget.TextView"));
  public SelenideElement okButton = $(By.id("android:id/button1"));

}
