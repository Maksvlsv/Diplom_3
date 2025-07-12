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

    private final By modalOverlay = By.className("Modal_modal__overlay__1xx9u");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Переключиться на вкладку 'Булки'")
    public void clickBunsTab() {
        clickTab(bunsTab);
    }

    @Step("Переключиться на вкладку 'Соусы'")
    public void clickSaucesTab() {
        clickTab(saucesTab);
    }

    @Step("Переключиться на вкладку 'Начинки'")
    public void clickFillingsTab() {
        clickTab(fillingsTab);
    }

    private void clickTab(By tabLocator) {
        waitForOverlayToDisappear();
        WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(tabLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", tab);

        try {
            tab.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
    }

    private void waitForOverlayToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
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