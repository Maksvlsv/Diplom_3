package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.MainPage;

public class ConstructorTabsTest extends BaseTest {

    @Test
    @DisplayName("Переключение на вкладку 'Булки'")
    public void checkSwitchToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForTabsVisible();

        Allure.step("Кликаем по вкладке 'Булки'", mainPage::clickBunsTab);
        Allure.step("Проверяем, что вкладка 'Булки' активна", () -> {
            assert mainPage.isBunsTabActive() : "Вкладка 'Булки' не активна";
        });
    }

    @Test
    @DisplayName("Переключение на вкладку 'Соусы'")
    public void checkSwitchToSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForTabsVisible();

        Allure.step("Кликаем по вкладке 'Соусы'", mainPage::clickSaucesTab);
        Allure.step("Проверяем, что вкладка 'Соусы' активна", () -> {
            assert mainPage.isSaucesTabActive() : "Вкладка 'Соусы' не активна";
        });
    }

    @Test
    @DisplayName("Переключение на вкладку 'Начинки'")
    public void checkSwitchToFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForTabsVisible();

        Allure.step("Кликаем по вкладке 'Начинки'", mainPage::clickFillingsTab);
        Allure.step("Проверяем, что вкладка 'Начинки' активна", () -> {
            assert mainPage.isFillingsTabActive() : "Вкладка 'Начинки' не активна";
        });
    }
}
