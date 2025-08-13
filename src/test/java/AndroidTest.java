import static com.codeborne.selenide.Condition.*;

import com.google.inject.Inject;
import pages.ExercisePage;
import extensions.MobileTestExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import pages.StartPage;

@ExtendWith(MobileTestExtension.class)
public class AndroidTest {
  @Inject
  StartPage startPage;

  @Inject
  MainPage mainPage;

  @Inject
  ExercisePage exercisePage;

  @Test()
  @DisplayName("Проверка отображения текста на стартовых экранах")
  public void testStartPage() {
    System.out.println("test steps");
    //1. Первый экран
    startPage.title1.shouldBe(visible)
        .shouldHave(text("Chat to improve your English"));
    //2. Прокрутка приветственного экрана
    startPage.buttonNext.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    //3. Второй экран
    startPage.title2.shouldBe(visible)
        .shouldHave(text("Learn new words and grammar"));
    //4. Прокрутка приветственного экрана
    startPage.buttonNext.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    //5. Третий экран
    startPage.title3.shouldBe(visible)
        .shouldHave(text("7 days FREE"));
    //6. Прокрутка приветственного экрана
    startPage.buttonPrice.shouldBe(visible)
        .shouldBe(enabled)
        .click();
    startPage.verifyCollectionTitles(startPage.buttonPriceText, "$6.99/month", "after trial period ends");
    //7. Кнопка пропустить
    startPage.skipButton.shouldBe(visible)
        .shouldBe(enabled)
        .click();
  }

  @Test()
  @DisplayName("Проверка отображения и кликабельности  вкладок главной страницы")
  public void testMainPage() {
    // переход на главную страницу при холодном старте
    startPage.skipStartPage();
    mainPage.closeAllertPopup();
    System.out.println("test steps");
    //1. Проверка наличия на экране вкладок
    mainPage.verifyCollectionTitles(mainPage.mainPageBlockTitles, "Chat", "Exercise", "Grammar", "Stats");
    //2. Проверка наличия кнопки "Premium"
    mainPage.premiumButton.shouldBe(visible)
        .shouldBe(enabled);
    //3. Проверка наличия экрана скролла и поля ввода сообщения
    mainPage.chatElement.scrollView.shouldBe(visible)
        .shouldBe(enabled);
    mainPage.chatElement.typeMessageField.shouldBe(visible)
        .shouldBe(enabled);
  }
  @Test()
  @DisplayName("Проверка запуска сценария выполнения задания обучения")
  public void testExercise() {
    // переход на главную страницу при холодном старте
    startPage.skipStartPage();
    mainPage.closeAllertPopup();
    System.out.println("test steps");
    //1. переход с главной страницы на экран задания
    mainPage.mainPageBlockTitles
        .get(1).click();
    //2.  клик на задание "Выучить 5 новых слов"
    exercisePage.exercisePageButton
        .shouldBe(visible).click();
    //3. проверка, что открылась главная страница чата и бот написал сообщение о старте урока
    mainPage.chatElement.messageInChatText("learn").get(0).shouldBe(visible);
    String[] startLessonMessageArray = "Let's learn some words! As always, you can write 'STOP' to exit the game\uD83D\uDE09".split(" ");
    mainPage.verifyCollectionTitles(mainPage.chatElement.messageInChatText("learn"),startLessonMessageArray);
    //4. ответить боту на 1 учебное сообщение нажатием на кнопку got It
    mainPage.chatElement.gotItButton
        .shouldBe(visible).click();
    //5. написать STOP, проверить, что сценарий обучения завершен
    mainPage.chatElement.typeMessageField
        .sendKeys("STOP");
    mainPage.chatElement.sendButton.click();
    String[] endLessonMessageArray = "Very good! Your vocabulary statistics: Learning: 1 words".split(" ");
    mainPage.verifyCollectionTitles(mainPage.chatElement.messageInChatText("statistics:"),endLessonMessageArray);
  }
}