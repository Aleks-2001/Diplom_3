//package pageobject.test;
//
//import io.qameta.allure.Step;
//import io.qameta.allure.junit4.DisplayName;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import pageobject.MainPage;
//import static com.codeborne.selenide.Condition.cssClass;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.open;
//
//public class ConstructorTest extends BaseUITest {
//
//    @Test
//    @DisplayName("Check of click to element 'Начинки'")
//    public void clickFillingsInConstructorTest() {
//        openMainPage();
//        clickAndCheckElement("Нажать на вкладку 'Начинки' в конструкторе", MainPage.elementFillingsLocator, "tab_tab_type_current__2BEPc");
//    }
//
//    @Test
//    @DisplayName("Check of click to element 'Соусы'")
//    public void clickSaucesInConstructorTest() {
//        openMainPage();
//        clickAndCheckElement("Нажать на вкладку 'Соусы' в конструкторе", MainPage.elementSaucesLocator, "tab_tab_type_current__2BEPc");
//    }
//
//    @Test
//    @DisplayName("Check of click to element 'Булки'")
//    public void clickRollsInConstructorTest() {
//        openMainPage();
//        // Вкладка "Булки" активна сразу, поэтому сначала кликнем на другую
//        $(MainPage.elementFillingsLocator).shouldBe(com.codeborne.selenide.Condition.visible).click();
//        clickAndCheckElement("Нажать на вкладку 'Булки' в конструкторе", MainPage.elementRollsLocator, "tab_tab_type_current__2BEPc");
//    }
//
//    @Step("Открыть главную страницу")
//    public void openMainPage() {
//        open(MainPage.Main_Page_URL);
//    }
//
//    @Step("{stepDescription}")
//    public void clickAndCheckElement(String stepDescription, By locator, String expectedClass) {
//        $(locator).shouldBe(com.codeborne.selenide.Condition.visible).click();
//        $(locator).shouldHave(cssClass(expectedClass));
//    }
//}
//
