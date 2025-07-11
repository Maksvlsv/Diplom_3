package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.MainPage;

public class ConstructorTabsTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        startBrowser(browser);

        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
        mainPage.waitForTabsVisible();
    }

    @Test
    @DisplayName("Переключение между вкладками конструктора")
    @Description("Проверка, что пользователь может переключаться между вкладками: Соусы, Начинки, Булки")
    public void checkConstructorTabsSwitching() {
        Allure.step("Кликаем по вкладке 'Соусы'", () -> {
            mainPage.clickSaucesTab();
        });
        Allure.step("Проверяем, что вкладка 'Соусы' активна", () -> {
            assert mainPage.isSaucesTabActive() : "Вкладка 'Соусы' не активна";
        });

        Allure.step("Кликаем по вкладке 'Начинки'", () -> {
            mainPage.clickFillingsTab();
        });
        Allure.step("Проверяем, что вкладка 'Начинки' активна", () -> {
            assert mainPage.isFillingsTabActive() : "Вкладка 'Начинки' не активна";
        });

        Allure.step("Кликаем по вкладке 'Булки'", () -> {
            mainPage.clickBunsTab();
        });
        Allure.step("Проверяем, что вкладка 'Булки' активна", () -> {
            assert mainPage.isBunsTabActive() : "Вкладка 'Булки' не активна";
        });
    }
}