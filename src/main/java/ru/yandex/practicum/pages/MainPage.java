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
    private static final By bunImg = By.xpath("//img[@alt='Флюоресцентная булка R2-D3']");

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

    @Step("Клик по разделу {sectionName} ")
    public void sectionClick(String sectionName) {
        By section = By.xpath(String.format(".//span[text()='%s']", sectionName));
        driver.findElement(section).click();
    }

    @Step("Проверка видимости заголовка раздела {sectionName}")
    public boolean isSectionVisible(String sectionName) {
        By sectionHeader = By.xpath(String.format(".//h2[text()='%s']", sectionName));
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(sectionHeader)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
