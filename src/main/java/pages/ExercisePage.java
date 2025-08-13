package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ExercisePage extends ACommon {

  public final SelenideElement exercisePageBlock = $(By.xpath("//android.widget.ScrollView/android.view.ViewGroup"));
  public final SelenideElement exercisePageText= exercisePageBlock.$(By.xpath(".//android.widget.TextView"));
  public final SelenideElement exercisePageButton= $(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup"));
}
