package pageobject.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import pageobject.*;
import elements.ButtonElement;
import elements.InputElement;
import static com.codeborne.selenide.Selenide.open;


public class RegistrationAndLoginTest extends BaseUITest {

    private User user;


    @Before
    @Step("before setUp")
    public void setUp() {
        // Создаем новый экземпляр юзера (через API)
        user = UserGenerator.getRandomUser();
    }


    @After
    @Step("after cleanUp")
    public void cleanUp() {
        // корректное удаление юзера (через API) после UI теста
        correctDeleteUser(user);
    }


    @Test
    @DisplayName("Check of registration new User")
    public void successfulRegistrationTest() {
        Allure.step("Открыть страницу регистрации", () -> open(RegisterPage.Reg_Page_URL));
        Allure.step("Заполняем форму регистрации", () -> {
            new InputElement(RegisterPage.nameInputLocator).setValue(user.getName());
            new InputElement(RegisterPage.emailInputLocator).setValue(user.getEmail());
            new InputElement(RegisterPage.passwordInputLocator).setValue(user.getPassword());
        });
        Allure.step("Нажать кнопку 'Зарегистрироваться'", () ->
                new ButtonElement(RegisterPage.registerButtonLocator).clickButton());
        Allure.step("Проверка, что открыта страница входа", () -> {
            Selenide.$(LoginPage.loginHeaderLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


//    @Test
//    @DisplayName("Check of registration new User with incorrect password")
//    public void registrationWithInvalidPasswordTest() {
//        Allure.step("Открыть страницу регистрации", () ->
//            open(RegisterPage.Reg_Page_URL));
//        Allure.step("Изменяем у юзера пароль на некорректный (менее 6 символов)", () ->
//            user.setPassword("test"));
//
//        Allure.step("Заполняем форму регистрации", () -> {
//            InputElement nameInput = new InputElement(RegisterPage.nameInputLocator);
//            nameInput.setValue(user.getName());
//            InputElement emailInput = new InputElement(RegisterPage.emailInputLocator);
//            emailInput.setValue(user.getEmail());
//            InputElement passwordInput = new InputElement(RegisterPage.passwordInputLocator);
//            passwordInput.setValue(user.getPassword());
//        });
//
//        Allure.step("Нажать кнопку 'Зарегистрироваться' и проверяем, что появилось сообщение об ошибке", () -> {
//            ButtonElement registerButton = new ButtonElement(RegisterPage.registerButtonLocator);
//            registerButton.clickButton();
//        Selenide.$(LoginPage.errorMessageLocator).shouldBe(com.codeborne.selenide.Condition.visible);
//        });
//    }
//
//
//    @Test
//    @DisplayName("Check of login using 'Login button' on the Main page")
//    public void loginUsingLoginButtonOnManePageTest() {
//        registerAndLogout(user);
//        Allure.step("Открываем главную страницу", () ->
//            open(MainPage.Main_Page_URL));
//        Allure.step("Нажимаем кнопку \"Войти в аккаунт\"", () -> {
//            ButtonElement loginButton = new ButtonElement(MainPage.loginButtonLocator);
//        loginButton.clickButton();
//        });
//        fillLoginForm(user);
//        checkSuccessfulLogin();
//    }
//
//
//    @Test
//    @DisplayName("Check of login using 'Личный кабинет' on the Main page")
//    public void loginUsingPersonalAccountButtonOnManePageTest() {
//        registerAndLogout(user);
//        Allure.step("Открываем главную страницу", () ->
//                open(MainPage.Main_Page_URL));
//        Allure.step("Нажимаем кнопку \"Личный кабинет\"", () -> {
//            ButtonElement personalAccountButton = new ButtonElement(MainPage.personalAccountButtonLocator);
//            personalAccountButton.clickButton();
//        });
//        fillLoginForm(user);
//        checkSuccessfulLogin();
//    }
//
//
//    @Test
//    @DisplayName("Check of login using 'Войти' on the Register page")
//    public void loginUsingLoginButtonOnRegisterPageTest() {
//        registerAndLogout(user);
//        Allure.step("Открываем страницу регистрации", () ->
//                open(RegisterPage.Reg_Page_URL));
//        Allure.step("Нажимаем кнопку \"Войти\" на странице регистрации", () -> {
//            ButtonElement loginButton = new ButtonElement(RegisterPage.loginButtonLocator);
//            loginButton.clickButton();
//        });
//        fillLoginForm(user);
//        checkSuccessfulLogin();
//    }
//
//
//    @Test
//    @DisplayName("Check of login using 'Войти' on the Forgot Password page")
//    public void loginUsingLoginButtonOnForgotPasswordPageTest() {
//        registerAndLogout(user);
//        Allure.step("Открываем страницу 'забыл пароль'", () ->
//                open(ForgotPasswordPage.Forgot_Password_Page_URL));
//        Allure.step("Нажимаем кнопку 'Войти' на странице восстановления пароля", () -> {
//            ButtonElement loginButton = new ButtonElement(ForgotPasswordPage.loginButtonLocator);
//            loginButton.clickButton();
//        });
//        fillLoginForm(user);
//        checkSuccessfulLogin();
//    }
//
//
//    @Test
//    @DisplayName("Check of logOut on Personal Account page")
//    public void logOutOnPersonalAccountPageTest() {
//        registerAndLogout(user); // Этот метод вполне подходит, так как все равно после регистрации необходимо войти
//        Allure.step("Открыть страницу авторизации", () ->
//                open(LoginPage.Login_Page_URL));
//        fillLoginForm(user);
//        Allure.step("Нажать кнопку 'Личный кабинет' на главной странице", () ->
//                new ButtonElement(MainPage.personalAccountButtonLocator).clickButton());
//        Allure.step("Нажать кнопку 'Выйти' в личном кабинете", () ->
//                new ButtonElement(PersonalAccountPage.logOutButtonLocator).clickButton());
//        Allure.step("Проверка, что выход осуществлен, по факту загрузки страницы входа", () -> {
//            Selenide.$(LoginPage.loginHeaderLocator).shouldBe(com.codeborne.selenide.Condition.visible);
//        });
//    }
//
//
//    @Test
//    @DisplayName("Check of click to 'Personal Account' button and open page")
//    public void clickToPersonalAccountButtonTest() {
//        registerAndLogout(user); // Этот метод вполне подходит, так как все равно после регистрации необходимо войти
//        Allure.step("Открыть страницу авторизации", () ->
//                open(LoginPage.Login_Page_URL));
//        fillLoginForm(user);
//        Allure.step("Нажать кнопку 'Личный кабинет' на главной странице", () ->
//                new ButtonElement(MainPage.personalAccountButtonLocator).clickButton());
//
//        Allure.step("Проверка входа в личный кабинет, по факту появления элемента 'История заказов'", () -> {
//            Selenide.$(PersonalAccountPage.historyButtonLocator).shouldBe(com.codeborne.selenide.Condition.visible);
//        });
//    }
//
//
//    @Test
//    @DisplayName("Check of click to 'Конструктор' element in the personal account")
//    public void clickToConstructorButtonTest() {
//        // Регистрируемся и заходим для корректного перехода на страницу личного кабинета.
//        registerAndLogout(user); // Этот метод вполне подходит, так как все равно после регистрации необходимо войти
//        Allure.step("Открыть страницу авторизации", () ->
//                open(LoginPage.Login_Page_URL));
//        fillLoginForm(user);
//        Allure.step("Нажать кнопку 'Личный кабинет' на главной странице", () ->
//                new ButtonElement(MainPage.personalAccountButtonLocator).clickButton());
//        Allure.step("Кликнуть по элементу 'Конструктор' в личном кабинет", () ->
//                new ButtonElement(PersonalAccountPage.constructorLocator).clickButton());
//        Allure.step("Проверка перехода в конструктор, по факту появления элемента 'Соберите бургер'", () -> {
//            Selenide.$(MainPage.elementOfConstructorLocator).shouldBe(com.codeborne.selenide.Condition.visible);
//        });
//    }
//
//
//    @Test
//    @DisplayName("Check of click to Logotype in the personal account")
//    public void clickToLogotypeTest() {
//        // Регистрируемся и входим для корректного перехода на страницу личного кабинета.
//        registerAndLogout(user); // Этот метод вполне подходит, так как все равно после регистрации необходимо войти
//        Allure.step("Открыть страницу авторизации", () -> {
//                open(LoginPage.Login_Page_URL);
//                attachScreenshot("Открыть страницу авторизации");
//    });
//        fillLoginForm(user);
//        Allure.step("Нажать кнопку 'Личный кабинет' на главной странице", () -> {
//                new ButtonElement(MainPage.personalAccountButtonLocator).clickButton();
//        attachScreenshot("Нажать кнопку 'Личный кабинет' на главной странице");
//    });
//        Allure.step("Кликнуть по элементу 'Логотип' в личном кабинет", () -> {
//                    new ButtonElement(PersonalAccountPage.logotypeLocator).clickButton();
//                    attachScreenshot("Кликнуть по элементу 'Логотип' в личном кабинет");
//                });
//
//        Allure.step("Проверка перехода в конструктор, по факту появления элемента 'Соберите бургер'", () -> {
//            Selenide.$(MainPage.elementOfConstructorLocator).shouldBe(com.codeborne.selenide.Condition.visible);
//            attachScreenshot("Проверка перехода в конструктор, по факту появления элемента 'Соберите бургер'");
//
//        });
//    }
//
//
//    @Attachment(value = "{attachName}", type = "image/png")
//    public byte[] attachScreenshot(String attachName) {
//        return Selenide.screenshot(OutputType.BYTES);
//    }


}
