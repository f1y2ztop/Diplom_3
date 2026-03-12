package ru.yandex.practicum.util;

public class DataForTesting {

    public static final Object[][]
                    registerTestData = {
            {"Алексей" ,"yandex12@yandex.ru", "validPassword", true},
            {"Константин", "pivozavr@mail.ru", "123", false}
    };

    public static final Object[][]
                    loginEntranceData = {
            {"Кнопка 'Войти в аккаунт' на главной", LoginType.MAIN_BUTTON},
            {"Кнопка 'Личный Кабинет' на главной", LoginType.PERSONAL_CABINET},
            {"Ссылка 'Войти' в форме регистрации", LoginType.REGISTRATION_FORM},
            {"Ссылка 'Войти' в форме восстановления пароля", LoginType.FORGOT_PASSWORD_FORM}
    };

    public static final Object[][]
                constructorSectionsData = {
            {"Булки"},
            {"Соусы"},
            {"Начинки"}
    };
}
