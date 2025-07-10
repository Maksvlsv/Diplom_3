package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    // Локаторы

    private final By profileHeader = By.xpath("//h2[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // Действия

    @Step("Проверить, что открыта страница профиля")
    public boolean isProfileHeaderVisible() {
        return driver.findElements(profileHeader).size() > 0;
    }

    @Step("Нажать 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать логотип Stellar Burgers")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Нажать 'Выход'")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}