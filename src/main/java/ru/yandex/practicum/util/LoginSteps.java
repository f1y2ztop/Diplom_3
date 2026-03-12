package ru.yandex.practicum.util;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.ForgotPasswordPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegistrationPage;
import static ru.yandex.practicum.util.LoginType.*;

public class LoginSteps {
    private final WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход к логину через {type}")
    public void navigateToLogin(LoginType type) {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        ForgotPasswordPage forPage = new ForgotPasswordPage(driver);

        switch (type) {
            case MAIN_BUTTON:
                mainPage.openMainPage();
                mainPage.loginAccButtonMainPageClick();
                break;
            case PERSONAL_CABINET:
                mainPage.openMainPage();
                mainPage.personalAccButtonMainPageClick();
                break;
            case REGISTRATION_FORM:
                regPage.openRegPage();
                regPage.loginLinkClick();
                break;
            case FORGOT_PASSWORD_FORM:
                forPage.openForPage();
                forPage.loginLinkClick();
                break;
        }
    }
}


