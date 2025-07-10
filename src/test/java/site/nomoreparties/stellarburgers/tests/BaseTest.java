package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Step;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    protected WebDriver driver;

    @Step("Запуск браузера: {browser}")
    public void startBrowser(String browser) {
        ChromeOptions options = new ChromeOptions();

        if ("yandex".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", "/Users/maks/dev/yandexdriver");
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}