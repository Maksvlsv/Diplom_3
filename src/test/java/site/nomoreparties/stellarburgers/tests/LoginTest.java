package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.*;
import site.nomoreparties.stellarburgers.utils.UserGenerator;

public class LoginTest extends BaseTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private String email;
    private final String password = UserGenerator.getDefaultPassword();

    @Before
    public void setUp() {
        // Создание пользователя через API
        email = UserGenerator.createRandomUser();
        Allure.addAttachment("Создан пользователь", email);

        // Запуск браузера
        String browser = System.getProperty("browser", "chrome");
        startBrowser(browser);
        driver.get(baseUrl);
        Allure.addAttachment("Браузер", browser);
    }

    @Test
    public void loginFromMainPageButton() {
        Allure.step("Нажать кнопку 'Войти в аккаунт' на главной странице", () -> {
            new MainPage(driver).clickLoginButton();
        });

        Allure.step("Заполнить форму логина и войти", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Проверить, что вход выполнен — отображается кнопка 'Оформить заказ'", () -> {
            new MainPage(driver).checkOrderButtonVisible();
        });
    }

    @Test
    public void loginFromPersonalCabinetButton() {
        Allure.step("Нажать кнопку 'Личный кабинет'", () -> {
            new MainPage(driver).clickProfileButton();
        });

        Allure.step("Заполнить форму логина и войти", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Проверить, что вход выполнен — отображается кнопка 'Оформить заказ'", () -> {
            new MainPage(driver).checkOrderButtonVisible();
        });
    }

    @Test
    public void loginViaRegisterForm() {
        Allure.step("Перейти на форму регистрации", () -> {
            new MainPage(driver).clickLoginButton();
            new LoginPage(driver).clickRegisterLink();
        });

        Allure.step("Нажать ссылку 'Войти' на форме регистрации", () -> {
            new RegisterPage(driver).clickLoginLink();
        });

        Allure.step("Заполнить форму логина и войти", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Проверить, что вход выполнен — отображается кнопка 'Оформить заказ'", () -> {
            new MainPage(driver).checkOrderButtonVisible();
        });
    }

    @Test
    public void loginViaForgotPasswordForm() {
        Allure.step("Перейти на форму восстановления пароля", () -> {
            new MainPage(driver).clickLoginButton();
            new LoginPage(driver).clickForgotPasswordLink();
        });

        Allure.step("Нажать ссылку 'Войти' на форме восстановления пароля", () -> {
            new ForgotPasswordPage(driver).clickLoginLink();
        });

        Allure.step("Заполнить форму логина и войти", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Проверить, что вход выполнен — отображается кнопка 'Оформить заказ'", () -> {
            new MainPage(driver).checkOrderButtonVisible();
        });
    }

    @After
    public void tearDownTest() {
        tearDown();
    }
}