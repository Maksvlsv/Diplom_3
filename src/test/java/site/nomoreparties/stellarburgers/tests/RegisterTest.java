package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.RegisterPage;
import site.nomoreparties.stellarburgers.utils.UserGenerator;


public class RegisterTest extends BaseTest {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private final String validPassword = UserGenerator.getDefaultPassword();
    private final String shortPassword = UserGenerator.getShortPassword();
    private String email;

    @Before
    public void createUser() {
        email = UserGenerator.createRandomUser();
        emailToDelete = email;
        Allure.addAttachment("Создан пользователь", email);
    }

    @Test
    public void successfulRegistration() {
        Allure.step("Открыть форму регистрации", () -> {
            new MainPage(driver).clickLoginButton();
            new LoginPage(driver).clickRegisterLink();
        });

        Allure.step("Заполнить форму: имя = Максим, email = " + email, () -> {
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

        Allure.step("Заполнить форму: имя = Ольга, email = " + email, () -> {
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
}