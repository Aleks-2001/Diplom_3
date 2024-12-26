package pageobject;

import org.openqa.selenium.By;

public class RegisterPage {

    public static final By registerButtonLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/button");
    public static final By nameInputLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    public static final By emailInputLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    public static final By passwordInputLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    public static final By loginButtonLocator = By.xpath(".//*[@id=\"root\"]/div/main/div/div/p/a");



    public static String Reg_Page_URL = "https://stellarburgers.nomoreparties.site/register";


}
