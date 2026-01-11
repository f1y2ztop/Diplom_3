package ru.yandex.practicum.util;

import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegistrationPage;

public class DataForTesting {

    public static final Object[][]
                    registerTestData = {
            {"Алексей" ,"yandex12@yandex.ru", "validPassword", true},
            {"Константин", "pivozavr@mail.ru", "123", false}
    };

    public static final Object[][]
                    loginEntranceData = {
            {"Вход через личный кабинет", EnvConfig.MAIN_URL, MainPage.personalAccButtonMainPage},
            {"Вход через кнопку Войти в аккаунт", EnvConfig.MAIN_URL, MainPage.loginAccButtonMainPage},
            {"Вход со страницы регистрации", EnvConfig.MAIN_URL + "register", RegistrationPage.loginPageLink},
            {"Вход со страницы забыли пароль", EnvConfig.MAIN_URL + "forgot-password", RegistrationPage.loginPageLink}
    };

    public static final Object[][]
                constructorSectionsData = {
            {"Булки"},
            {"Соусы"},
            {"Начинки"}
    };
}
