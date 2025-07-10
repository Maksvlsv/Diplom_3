package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Действия

    @Step("Нажать 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажать 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать логотип Stellar Burgers")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Переключиться на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Переключиться на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Переключиться на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается (вход выполнен)")
    public void checkOrderButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Оформить заказ']")));
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        driver.findElement(personalAccountButton).click();
    }
}