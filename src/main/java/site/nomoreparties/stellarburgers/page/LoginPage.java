package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы
    private final By emailField = By.xpath("//input[@name='name' or @name='email']");
    private final By passwordField = By.xpath("//input[@name='Пароль' or @name='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By forgotPasswordLink = By.linkText("Восстановить пароль");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Действия

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Перейти по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Перейти по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }
}