import static com.codeborne.selenide.Condition.*;

import com.google.inject.Inject;
import pages.elements.AllertPopup;
import extensions.MobileTestExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import pages.StartPage;

@ExtendWith(MobileTestExtension.class)
public class test {
  @Inject
  StartPage startPage;

  @Inject
  MainPage mainPage;

  @Inject
  AllertPopup allertPopup;

//  @Inject
//  ExercisePage exercisePage;

  @Test()
  @DisplayName("Проверка отображения текста на стартовых экранах")
  public void testStartPage() {
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
    startPage.verifyCollectionTitles( startPage.buttonPriceText, "$6.99/month", "after trial period ends");
    //7. Кнопка пропустить
    startPage.skipButton.shouldBe(visible)
        .shouldBe(enabled)
        .click();
  }

  @Test()
  @DisplayName("Проверка отображения и кликабельности  вкладок главной страницы")
  public void testMainPage() {
    //startPage.skipStartPage();
    mainPage.closeAllertPopup();
    //1. Проверка наличия на экране вкладок
    mainPage.verifyCollectionTitles(mainPage.mainPageBlockTitles, "Chat", "Exercise", "Grammar" ,"Stats");
    //2. Проверка наличия кнопки "Premium"
    mainPage.PremiumButton.shouldBe(visible)
        .shouldBe(enabled);
    //3. Проверка наличия экрана скролла и поля ввода сообщения
    mainPage.chatElement.scrollView.shouldBe(visible)
        .shouldBe(enabled);
    mainPage.chatElement.typeMessageField.shouldBe(visible)
        .shouldBe(enabled);
    //4. Клик всех вкладок
//    mainPage.mainPageBlockFolders.stream().forEach(folder -> folder.click());
  }
//  @Test()
//  @DisplayName("Проверка запуска сценария выполнения задания обучения")
//  public void testExercise() {
//    //startPage.skipStartPage();
//    mainPage.closeAllertPopup();
//    //стартовый размер чата
//    //int startSizeOfChat = mainPage.collectionCounter(mainPage.chatElement.scrollViewElements,1);
//    System.out.println("стартовый размер чата");
//    // переход с главной страницы на экран задания
//    mainPage.mainPageBlockFolders
//        .get(1)
//        .click();
//    System.out.println("переход с главной страницы на экран задания");
////    // клик на задание "Выучить 5 новых слов"
////    exercisePage.exercisePageButton
////        .shouldBe(visible)
////        .shouldBe(clickable)
////        .click();
//    // проверка, что открылась главная страница чата и бот написал сообщение о старте
//    mainPage
//        .verifyCollectionTitles(mainPage.mainPageBlockTitles, "Chat", "Exercise", "Grammar" ,"Stats");
//    String[] startLessonMessageArray = "Let's learn some words! As always, you can write 'STOP' to exit the game\uD83D\uDE09".split(" ");
//    mainPage.verifyCollectionTitles(mainPage.chatElement.messageInChatText("learn"),startLessonMessageArray);
//    System.out.println("проверка, что открылась главная страница чата и бот написал сообщение о старте");
//    //int firstRowChatSize = mainPage.collectionCounter(mainPage.chatElement.scrollViewElements,startSizeOfChat+2);
//    // ответить боту на сообщение нажатием на кнопку got It
//    mainPage.chatElement.gotItButton
//        .shouldBe(visible)
//        .click();
//    System.out.println("ответить боту на сообщение нажатием на кнопку got It");
//    // написать STOP, проверить, что сценарий завершен
//    //int secondRowChatSize = mainPage.collectionCounter(mainPage.chatElement.scrollViewElements,firstRowChatSize+3);
//    String[] endLessonMessageArray = "Very good! Your vocabulary statistics: Learning: 1 words".split(" ");
//    mainPage.chatElement.typeMessageField
//        .setValue("STOP");
//    mainPage.verifyCollectionTitles(mainPage.chatElement.messageInChatText("statistics:"),endLessonMessageArray);
//    System.out.println("ответить боту на сообщение нажатием на кнопку got It");
//    //int finalRowChatSize = mainPage.collectionCounter(mainPage.chatElement.scrollViewElements,secondRowChatSize+2);
//  }
}