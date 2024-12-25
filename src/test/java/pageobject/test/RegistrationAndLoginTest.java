package pageobject.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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


    @Test
    @DisplayName("Check of registration new User with incorrect password")
    public void registrationWithInvalidPasswordTest() {
        Allure.step("Открыть страницу регистрации", () ->
            open(RegisterPage.Reg_Page_URL));
        Allure.step("Изменяем у юзера пароль на некорректный (менее 6 символов)", () ->
            user.setPassword("test"));

        Allure.step("Заполняем форму регистрации", () -> {
            InputElement nameInput = new InputElement(RegisterPage.nameInputLocator);
            nameInput.setValue(user.getName());
            InputElement emailInput = new InputElement(RegisterPage.emailInputLocator);
            emailInput.setValue(user.getEmail());
            InputElement passwordInput = new InputElement(RegisterPage.passwordInputLocator);
            passwordInput.setValue(user.getPassword());
        });

        Allure.step("Нажать кнопку 'Зарегистрироваться' и проверяем, что появилось сообщение об ошибке", () -> {
            ButtonElement registerButton = new ButtonElement(RegisterPage.registerButtonLocator);
            registerButton.clickButton();
        Selenide.$(LoginPage.errorMessageLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


    @Test
    @DisplayName("Check of login using 'Login button' on the Main page")
    public void loginUsingLoginButtonOnManePageTest() {
        registerAndLogout(user);
        Allure.step("Открываем главную страницу", () ->
            open(MainPage.Main_Page_URL));
        Allure.step("Нажимаем кнопку \"Войти в аккаунт\"", () -> {
            ButtonElement loginButton = new ButtonElement(MainPage.loginButtonLocator);
        loginButton.clickButton();
        });

        Allure.step("Заполняем форму входа", () -> {
            InputElement emailInput = new InputElement(LoginPage.emailInputLocator);
            emailInput.setValue(user.getEmail());
            InputElement passwordInput = new InputElement(LoginPage.passwordInputLocator);
            passwordInput.setValue(user.getPassword());
        });

        Allure.step("Нажать кнопку 'Войти'", () -> {
            ButtonElement registerButton = new ButtonElement(LoginPage.loginButtonLocator);
            registerButton.clickButton();
        });

        Allure.step("Проверяем, что вход осуществлён по факту появления кнопки 'Оформить заказ' на главной странице", () -> {
            Selenide.$(MainPage.makeOrderButtonLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


    @Test
    @DisplayName("Check of login using 'Личный кабинет' on the Main page")
    public void loginUsingPersonalAccountButtonOnManePageTest() {
        registerAndLogout(user);
        Allure.step("Открываем главную страницу", () ->
                open(MainPage.Main_Page_URL));
        Allure.step("Нажимаем кнопку \"Личный кабинет\"", () -> {
            ButtonElement personalAccountButton = new ButtonElement(MainPage.personalAccountButtonLocator);
            personalAccountButton.clickButton();
        });

        Allure.step("Заполняем форму входа", () -> {
            InputElement emailInput = new InputElement(LoginPage.emailInputLocator);
            emailInput.setValue(user.getEmail());
            InputElement passwordInput = new InputElement(LoginPage.passwordInputLocator);
            passwordInput.setValue(user.getPassword());
        });

        Allure.step("Нажать кнопку 'Войти'", () -> {
            ButtonElement registerButton = new ButtonElement(LoginPage.loginButtonLocator);
            registerButton.clickButton();
        });

        Allure.step("Проверяем, что вход осуществлён по факту появления кнопки 'Оформить заказ' на главной странице", () -> {
            Selenide.$(MainPage.makeOrderButtonLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


    @Test
    @DisplayName("Check of login using 'Войти' on the Register page")
    public void loginUsingLoginButtonOnRegisterPageTest() {
        registerAndLogout(user);
        Allure.step("Открываем страницу регистрации", () ->
                open(RegisterPage.Reg_Page_URL));
        Allure.step("Нажимаем кнопку \"Войти\" на странице регистрации", () -> {
            ButtonElement loginButton = new ButtonElement(RegisterPage.loginButtonLocator);
            loginButton.clickButton();
        });

        Allure.step("Заполняем форму входа", () -> {
            InputElement emailInput = new InputElement(LoginPage.emailInputLocator);
            emailInput.setValue(user.getEmail());
            InputElement passwordInput = new InputElement(LoginPage.passwordInputLocator);
            passwordInput.setValue(user.getPassword());
        });

        Allure.step("Нажать кнопку 'Войти'", () -> {
            ButtonElement registerButton = new ButtonElement(LoginPage.loginButtonLocator);
            registerButton.clickButton();
        });

        Allure.step("Проверяем, что вход осуществлён по факту появления кнопки 'Оформить заказ' на главной странице", () -> {
            Selenide.$(MainPage.makeOrderButtonLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


    @Test
    @DisplayName("Check of login using 'Войти' on the Forgot Password page")
    public void loginUsingLoginButtonOnForgotPasswordPageTest() {
        registerAndLogout(user);
        Allure.step("Открываем страницу 'забыл пароль'", () ->
                open(ForgotPasswordPage.Forgot_Password_Page_URL));
        Allure.step("Нажимаем кнопку 'Войти' на странице восстановления пароля", () -> {
            ButtonElement loginButton = new ButtonElement(ForgotPasswordPage.loginButtonLocator);
            loginButton.clickButton();
        });

        Allure.step("Заполняем форму входа", () -> {
            InputElement emailInput = new InputElement(LoginPage.emailInputLocator);
            emailInput.setValue(user.getEmail());
            InputElement passwordInput = new InputElement(LoginPage.passwordInputLocator);
            passwordInput.setValue(user.getPassword());
        });

        Allure.step("Нажать кнопку 'Войти'", () -> {
            ButtonElement registerButton = new ButtonElement(LoginPage.loginButtonLocator);
            registerButton.clickButton();
        });

        Allure.step("Проверяем, что вход осуществлён по факту появления кнопки 'Оформить заказ' на главной странице", () -> {
            Selenide.$(MainPage.makeOrderButtonLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


    @Test
    @DisplayName("Check of logOut on Personal Account page")
    public void logOutOnPersonalAccountPageTest() {
        registerAndLogout(user);
        Allure.step("Открыть страницу авторизации", () ->
                open(LoginPage.Login_Page_URL));
        Allure.step("Заполняем форму входа", () -> {
            InputElement emailInput = new InputElement(LoginPage.emailInputLocator);
            emailInput.setValue(user.getEmail());
            InputElement passwordInput = new InputElement(LoginPage.passwordInputLocator);
            passwordInput.setValue(user.getPassword());
        });
        Allure.step("Нажать кнопку 'Войти'", () -> {
            ButtonElement registerButton = new ButtonElement(LoginPage.loginButtonLocator);
            registerButton.clickButton();
        });
        Allure.step("Нажать кнопку 'Личный кабинет' на главной странице", () ->
                new ButtonElement(MainPage.personalAccountButtonLocator).clickButton());
        Allure.step("Нажать кнопку 'Выйти' в личном кабинете", () ->
                new ButtonElement(PersonalAccountPage.logOutButtonLocator).clickButton());
        Allure.step("Проверка, что выход осуществлен, по факту загрузки страницы входа", () -> {
            Selenide.$(LoginPage.loginHeaderLocator).shouldBe(com.codeborne.selenide.Condition.visible);
        });
    }


//    Переход в личный кабинет
//    Проверь переход по клику на «Личный кабинет».
//    Переход из личного кабинета в конструктор
//    Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.


//    Проверь, что работают переходы к разделам:
//            «Булки»,
//            «Соусы»,
//            «Начинки».
}
