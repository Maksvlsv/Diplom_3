package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.PersonalAccountPage;
import site.nomoreparties.stellarburgers.utils.UserGenerator;

public class AccountNavigationTest extends BaseTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private String email;
    private final String password = UserGenerator.getDefaultPassword();

    @Before
    public void createUser() {
        email = UserGenerator.createRandomUser();
        emailToDelete = email;
        Allure.addAttachment("Создан пользователь", email);
    }

    @Test
    public void navigateToAccountAndBackToConstructor() {
        Allure.step("Войти в аккаунт", () -> {
            new MainPage(driver).clickLoginButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Перейти в 'Личный кабинет'", () -> {
            new MainPage(driver).clickProfileButton();
            new PersonalAccountPage(driver).waitForLogoutButtonVisible();
        });

        Allure.step("Нажать на кнопку 'Конструктор'", () -> {
            new PersonalAccountPage(driver).clickConstructorButton();
        });

        Allure.step("Проверить, что отображается кнопка 'Оформить заказ'", () -> {
            new MainPage(driver).checkOrderButtonVisible();
        });
    }

    @Test
    public void navigateToAccountAndClickLogo() {
        Allure.step("Войти в аккаунт", () -> {
            new MainPage(driver).clickLoginButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Перейти в 'Личный кабинет'", () -> {
            new MainPage(driver).clickProfileButton();
            new PersonalAccountPage(driver).waitForLogoutButtonVisible();
        });

        Allure.step("Нажать на логотип Stellar Burgers", () -> {
            new PersonalAccountPage(driver).clickLogo();
        });

        Allure.step("Проверить, что отображается кнопка 'Оформить заказ'", () -> {
            new MainPage(driver).checkOrderButtonVisible();
        });
    }

    @Test
    public void logoutFromAccount() {
        Allure.step("Войти в аккаунт", () -> {
            new MainPage(driver).clickLoginButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLoginButton();
        });

        Allure.step("Перейти в 'Личный кабинет'", () -> {
            new MainPage(driver).clickProfileButton();
        });

        Allure.step("Нажать кнопку 'Выход'", () -> {
            PersonalAccountPage accountPage = new PersonalAccountPage(driver);
            accountPage.waitForLogoutButtonVisible();
            accountPage.clickLogoutButton();
        });

        Allure.step("Проверить, что отображается форма логина", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoginFormVisible();
        });
    }


}