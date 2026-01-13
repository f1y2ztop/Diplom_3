package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.EnvConfig;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By loginPageLink = By.cssSelector(".Auth_link__1fOlj");

    @Step("Открыть страницу 'Забыли пароль'")
    public void openForPage() {
        driver.get(EnvConfig.MAIN_URL + "register");
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(loginPageLink));
    }

    @Step("Кликнуть по ссылке на логин пользователя")
    public void loginLinkClick() {
        driver.findElement(loginPageLink).click();
    }
}