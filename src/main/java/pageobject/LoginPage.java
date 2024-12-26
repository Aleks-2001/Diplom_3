package pageobject;

import org.openqa.selenium.By;

public class LoginPage {

    public static final By loginButtonLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/button");
    public static final By loginHeaderLocator = By.xpath("//h2[text()='Вход']");
    public static final By errorMessageLocator = By.xpath("//p[text()='Некорректный пароль']");
    public static final By emailInputLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    public static final By passwordInputLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");

    public static String Login_Page_URL = "https://stellarburgers.nomoreparties.site/login";


}
