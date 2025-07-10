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

import static org.hamcrest.Matchers.equalTo;

public class RegisterTest extends BaseTest {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private final String validPassword = "123456";
    private final String shortPassword = "123";
    private String email;

    @Before
    public void setUp() {
        //Запуск браузера
        String browser = System.getProperty("browser", "chrome");
        startBrowser(browser);
        driver.get(baseUrl);
        Allure.addAttachment("Браузер", browser);

        //Проверка успешной регистрации
        email = UserGenerator.generateRandomEmail();
        Allure.step("Создан пользователь с email: " + email);
    }


    @Test
    public void successfulRegistration() {
        email = UserGenerator.generateRandomEmail();

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
        email = generateEmail();

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

    private String generateEmail() {
        return UUID.randomUUID() + "@yandex.ru";
    }
}