package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import site.nomoreparties.stellarburgers.utils.UserGenerator;

public abstract class BaseTest {
    protected WebDriver driver;
    protected final String baseUrl = "https://stellarburgers.nomoreparties.site/";

    // 👇 Поля для удаления пользователя
    protected String emailToDelete;
    protected final String defaultPassword = UserGenerator.getDefaultPassword();

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        startBrowser(browser);
        driver.get(baseUrl);
        Allure.addAttachment("Браузер", browser);
    }

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
        Allure.step("Закрытие браузера");
        if (driver != null) {
            driver.quit();
        }

        if (emailToDelete != null) {
            Allure.step("Удаление тестового пользователя через API", () -> {
                try {
                    String token = UserGenerator.getAccessToken(emailToDelete, defaultPassword);
                    UserGenerator.deleteUser(token);
                    Allure.addAttachment("Удалён пользователь", emailToDelete);
                } catch (Exception e) {
                    Allure.step("Ошибка при удалении пользователя: " + e.getMessage());
                }
            });
        }
    }
}