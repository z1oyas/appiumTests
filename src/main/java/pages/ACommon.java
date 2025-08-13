package pages;

import static com.codeborne.selenide.Condition.text;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

public abstract class ACommon {

  public void verifyCollectionTitles(ElementsCollection elements, String... texts) {
    for (int i = 0; i < texts.length; i++) {
      elements.get(i).shouldHave(text(texts[i]));
    }
  }

  public Integer collectionCounter(ElementsCollection elements, int size) {
    return elements.shouldBe(CollectionCondition.size(size)).size();
  }

}
