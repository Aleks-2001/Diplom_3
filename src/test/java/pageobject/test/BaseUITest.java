package pageobject.test;

import api.RequestAPI;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import elements.ButtonElement;
import elements.InputElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import pageobject.LoginPage;
import pageobject.MainPage;

import java.io.IOException;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.hamcrest.Matchers.equalTo;

public class BaseUITest {

    @Before
    public void startUp() throws IOException {
        initDriver();
        Configuration.timeout = 5000;
    }

    @After
    public void
    tearDown() {
        closeWebDriver();
    }


    @Step("Registration new user and LogOut for test")
    public void registerAndLogout(User user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api/";
        RequestAPI requestAPI = new RequestAPI();
        // вызываем метод отправки запроса для регистрации нового юзера
        ValidatableResponse response = requestAPI.sendRequest(user, "auth/register", "post");
        // проверка успешной регистрации
        response.log().all()
                .assertThat().statusCode(200)
                .and().body("success", equalTo(true));
        // Извлекаем из ответа сервера refreshToken
        String refreshTokenValue = response
                .extract()
                .response()
                .jsonPath()
                .getString("refreshToken");
        System.out.println("Извлечённый РефрешТокен:  " + refreshTokenValue);

        // выходим из системы после регистрации (logOut)
        ValidatableResponse responseLogOut = requestAPI.sendLogOutRequest(refreshTokenValue);
        // проверка успешного выхода
        responseLogOut.log().all()
                .assertThat().statusCode(200)
                .and().body("success", equalTo(true));
    }


    @Step("Fill login form and enter")
    public void fillLoginForm (User user) {
        InputElement emailInput = new InputElement(LoginPage.emailInputLocator);
        emailInput.setValue(user.getEmail());
        InputElement passwordInput = new InputElement(LoginPage.passwordInputLocator);
        passwordInput.setValue(user.getPassword());
        ButtonElement registerButton = new ButtonElement(LoginPage.loginButtonLocator);
        registerButton.clickButton();
    }


    @Step("Check successful login")
    public void checkSuccessfulLogin () {
    // Проверяем, что вход осуществлён по факту появления кнопки 'Оформить заказ' на главной странице
        Selenide.$(MainPage.makeOrderButtonLocator).shouldBe(com.codeborne.selenide.Condition.visible);
    }


    @Step("Correct delete user")
    public void correctDeleteUser(User user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api/";
        RequestAPI requestAPI = new RequestAPI();
        // Проверка авторизации с целью получения токенов
        Allure.step("Check authorization for to get tokens");
        ValidatableResponse response = requestAPI.sendAuthorizationRequest(user);
        response.log().all(); // Логируем весь ответ для проверки
        // Извлечение токена
        String accessTokenValue = response.extract().response().jsonPath().getString("accessToken");
        // удаление юзера после теста (если токена нет - удаление не требуется).
        if (accessTokenValue != null) {
            Allure.step("Delete, if \"accessToken\" is not null");
            ValidatableResponse responseDelete = requestAPI.sendUserDeleteRequest(accessTokenValue);
            // проверка удаления юзера
            responseDelete.log().all()
                    .assertThat().statusCode(202)
                    .and().body("message", equalTo("User successfully removed"));
            System.out.println("User успешно удалён.");
        }
    }
}
