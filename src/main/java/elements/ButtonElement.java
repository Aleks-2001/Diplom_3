package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class ButtonElement {
    SelenideElement buttonElement;

    public ButtonElement (By locator){
        buttonElement = $(locator);
    }

    public void clickButton() {
        buttonElement.shouldBe(enabled);
        buttonElement.scrollIntoView(true);
        buttonElement.click();
    }
}
