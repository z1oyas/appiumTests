package pages.elements;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.ACommon;

public class ChatElement extends ACommon {

  public SelenideElement gotItButton = $(By.xpath("//android.widget.TextView[contains(@text, \"Got it\")]"));
  public SelenideElement iKnowButton = $(By.xpath("//android.widget.TextView[contains(@text, \"I know this word\")]"));
  public SelenideElement explainButton = $(By.xpath("//android.widget.TextView[contains(@text, \"Explain\")]"));
  public  SelenideElement approveMessage = $(By.xpath("//android.widget.TextView[@text=\"Great!\"]"));
  public  SelenideElement typeMessageField = $(By.className("android.widget.EditText"));
  public  SelenideElement scrollView = $(By.className("android.widget.ScrollView"));
  public  ElementsCollection scrollViewElements = scrollView.$$(By.className(".//android.view.ViewGroup/android.view.ViewGroup"));


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
