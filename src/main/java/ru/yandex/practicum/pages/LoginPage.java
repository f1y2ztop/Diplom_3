package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.EnvConfig;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private static final By emailLoginField = By.xpath(".//fieldset[1]//input");
    private static final By passwordLoginField = By.xpath(".//fieldset[2]//input");
    private static final By loginButtonLoginPage = By.xpath(".//button[text()='Войти']");
    private static final By registrationLink = By.xpath(".//a[text()='Зарегистрироваться']");

    @Step("Логин. Заполнить поле 'Email' значением {emailData}")
    public void fillEmailLoginField(String emailData) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(emailLoginField)).sendKeys(emailData);
    }

    @Step("Логин. Заполнить поле 'Пароль' значением {passwordData}")
    public void fillPasswordLoginPage(String passwordData) {
        driver.findElement(passwordLoginField).sendKeys(passwordData);
    }

    @Step("Логин. Кликнуть по кнопке 'Войти'")
    public void loginButtonLoginPageClick() {
        driver.findElement(loginButtonLoginPage).click();
    }

    @Step("Кликнуть по ссылке регистрации")
    public void registrationLinkClick() {
        driver.findElement(registrationLink).click();
    }

    @Step("Проверка видимости кнопки 'Войти'")
    public boolean isLoginButtonDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(loginButtonLoginPage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
