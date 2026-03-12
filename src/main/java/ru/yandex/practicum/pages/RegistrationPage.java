package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.EnvConfig;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By nameRegField = By.xpath(".//fieldset[1]//input");
    private static final By emailRegField = By.xpath(".//fieldset[2]//input");
    private static final By passwordRegField = By.xpath(".//input[@name='Пароль']");
    private static final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public static final By loginPageLink = By.cssSelector(".Auth_link__1fOlj"); // Локатор идентичен для страницы forgot-password
    private static final By invalidPassword = By.xpath(".//p[text()='Некорректный пароль']");

    @Step("Открыть страницу регистрации")
            public void openRegPage() {
        driver.get(EnvConfig.MAIN_URL + "register");
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(nameRegField));
    }

    @Step("Регистрация. Заполнить поле 'Имя' значением: {name}")
    public void fillNameRegField(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(nameRegField)).sendKeys(name);
    }

    @Step("Регистрация. Заполнить поле 'Email' значением: {email}")
    public void fillEmailRegField(String email) {
        driver.findElement(emailRegField).sendKeys(email);
    }

    @Step("Регистрация. Заполнить поле 'Password' значением: {password}")
    public void fillPasswordRegField(String password) {
        driver.findElement(passwordRegField).sendKeys(password);
    }

    @Step("Кликнуть по кнопке регистрации пользователя")
    public void registrationButtonClick() {
        driver.findElement(registrationButton).click();
    }
    @Step("Кликнуть по ссылке на логин пользователя")
    public void loginLinkClick() {
        driver.findElement(loginPageLink).click();
    }
    @Step("Проверка видимости ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(invalidPassword));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
