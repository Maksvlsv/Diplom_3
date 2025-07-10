package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.RegisterPage;
import site.nomoreparties.stellarburgers.utils.UserGenerator;

import java.util.UUID;

public class RegisterTest extends BaseTest {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private final String validPassword = UserGenerator.getDefaultPassword();
    private final String shortPassword = UserGenerator.getShortPassword();
    private String email;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        startBrowser(browser);
        driver.get(baseUrl);
        Allure.addAttachment("Браузер", browser);

        email = UserGenerator.generateRandomEmail();
        Allure.step("Сгенерирован email для регистрации: " + email);
    }

    @Test
    public void successfulRegistration() {
        Allure.step("Открыть форму регистрации", () -> {
            new MainPage(driver).clickLoginButton();
            new LoginPage(driver).clickRegisterLink();
        });

        Allure.step("Заполнить форму: имя = Макс, email = " + email, () -> {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.setName("Макс");
            registerPage.setEmail(email);
            registerPage.setPassword(validPassword);
        });

        Allure.step("Нажать кнопку 'Зарегистрироваться'", () -> {
            new RegisterPage(driver).clickRegisterButton();
        });

        Allure.step("Проверить, что произошёл переход на форму логина", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
        });
    }

    @Test
    public void registrationWithShortPasswordShowsError() {
        email = UserGenerator.generateRandomEmail();

        Allure.step("Открыть форму регистрации", () -> {
            new MainPage(driver).clickLoginButton();
            new LoginPage(driver).clickRegisterLink();
        });

        Allure.step("Заполнить форму: имя = Миша, email = " + email, () -> {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.setName("Миша");
            registerPage.setEmail(email);
            registerPage.setPassword(shortPassword);
        });

        Allure.step("Нажать кнопку 'Зарегистрироваться'", () -> {
            new RegisterPage(driver).clickRegisterButton();
        });

        Allure.step("Проверить отображение ошибки о некорректном пароле", () -> {
            RegisterPage registerPage = new RegisterPage(driver);
            assert registerPage.isPasswordErrorVisible();
        });
    }

    @After
    public void tearDownTest() {
        tearDown();
    }
}