package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    private final By activeBunsTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]/span[text()='Булки']");
    private final By activeSaucesTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]/span[text()='Соусы']");
    private final By activeFillingsTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]/span[text()='Начинки']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Действия

    @Step("Нажать 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Переключиться на вкладку 'Булки'")
    public void clickBunsTab() {
        WebElement buns = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(bunsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buns);
        buns.click();
    }

    @Step("Переключиться на вкладку 'Соусы'")
    public void clickSaucesTab() {
        WebElement sauces = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saucesTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sauces);
        sauces.click();
    }

    @Step("Переключиться на вкладку 'Начинки'")
    public void clickFillingsTab() {
        WebElement fillings = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(fillingsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fillings);
        fillings.click();
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

    @Step("Ожидать, что вкладки конструктора видимы и кликабельны")
    public void waitForTabsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(bunsTab));
    }

    @Step("Проверить, что вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        return !driver.findElements(activeBunsTab).isEmpty();
    }

    @Step("Проверить, что вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        return !driver.findElements(activeSaucesTab).isEmpty();
    }

    @Step("Проверить, что вкладка 'Начинки' активна")
    public boolean isFillingsTabActive() {
        return !driver.findElements(activeFillingsTab).isEmpty();
    }
}