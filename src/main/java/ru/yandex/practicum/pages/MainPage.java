package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.EnvConfig;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By loginAccButtonMainPage = By.xpath(".//button[text()='Войти в аккаунт']");
    public static final By personalAccButtonMainPage = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By bunImg = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    private static final By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    private final By bunsTab = By.xpath("//div[span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[span[text()='Начинки']]");

    private final String activeClass = "tab_tab_type_current";

    @Step("Открыть главную страницу")
    public void openMainPage() {
        driver.get(EnvConfig.MAIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(bunImg));
    }

    @Step("Главная страница. Кликнуть по кнопке 'Войти в аккаунт'")
    public void loginAccButtonMainPageClick() {
        driver.findElement(loginAccButtonMainPage).click();
    }

    @Step("Главная страница. Кликнуть по кнопке 'Личный кабинет'")
    public void personalAccButtonMainPageClick() {
        driver.findElement(personalAccButtonMainPage).click();
    }

    @Step("Клик по разделу {sectionName} ")
    public void sectionClick(String sectionName) {
        By section = By.xpath(String.format(".//span[text()='%s']", sectionName));
        driver.findElement(section).click();
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Клик на раздел 'Булки'")
    public void clickBunsSection() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик на раздел 'Соусы'")
    public void clickSaucesSection() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик на раздел 'Начинки'")
    public void clickFillingsSection() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверка активности вкладки через атрибут class")
    public boolean isTabActive(By tabLocator) {
        String className = driver.findElement(tabLocator).getAttribute("class");
        return className.contains("tab_tab_type_current");
    }

    @Step("Проверить, активен ли раздел 'Булки'")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, активен ли раздел 'Соусы'")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, активен ли раздел 'Начинки'")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }
}
