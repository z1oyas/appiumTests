package pages.elements;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.ACommon;

public class ChatElement extends ACommon {

  public final SelenideElement gotItButton = $(By.xpath("//android.widget.TextView[contains(@text, \"Got it\")]"));
  public final SelenideElement knowButton = $(By.xpath("//android.widget.TextView[contains(@text, \"I know this word\")]"));
  public final SelenideElement explainButton = $(By.xpath("//android.widget.TextView[contains(@text, \"Explain\")]"));
  public final SelenideElement approveMessage = $(By.xpath("//android.widget.TextView[@text=\"Great!\"]"));
  public final SelenideElement typeMessageField = $(By.className("android.widget.EditText"));
  public final SelenideElement sendButton = $(By.xpath("//android.widget.TextView[@text=\"Send\"]"));
  public final SelenideElement scrollView = $(By.xpath("//android.widget.ScrollView"));
  public final ElementsCollection scrollViewElements = scrollView.$$(By.className(".//android.view.ViewGroup/android.view.ViewGroup"));


  private String selectorForMessage(String text) {
    return "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text=\""+text+"\"]/../..";
  }
  private SelenideElement messageInChat(String text) {
    return $(By.xpath(selectorForMessage(text)));
  }
  public ElementsCollection messageInChatText(String text) {
    return messageInChat(text).$$(By.xpath(".//android.view.ViewGroup/android.widget.TextView"));
  }
}
