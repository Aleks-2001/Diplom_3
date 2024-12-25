package pageobject;

import org.openqa.selenium.By;

public class MainPage {

    public static final By loginButtonLocator = By.xpath(".//*[@id=\"root\"]/div/main/section[2]/div/button");
    public static final By makeOrderButtonLocator = By.xpath(".//button[text() = 'Оформить заказ']");
    public static final By personalAccountButtonLocator = By.xpath(".//*[@id=\"root\"]/div/header/nav/a/p");

    public static String Main_Page_URL = "https://stellarburgers.nomoreparties.site";






}

