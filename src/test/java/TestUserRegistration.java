import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegistrationPage;
import ru.yandex.practicum.util.DataForTesting;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestUserRegistration extends BaseTest {

    public final String name;
    public final String email;
    public final String password;
    private final boolean expectedResult;

    public TestUserRegistration(String name, String email, String password, boolean expectedResult) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters(name = "Имя: {0}, Email: {1}, Пароль: {2}, Результат: {3}")
    public static Collection<Object[]> getRegistration() {
        return Arrays.asList(DataForTesting.registerTestData);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void testUserRegistration() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.loginAccButtonMainPageClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registrationLinkClick();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillNameRegField(name);
        registrationPage.fillEmailRegField(email);
        registrationPage.fillPasswordRegField(password);
        registrationPage.registrationButtonClick();
        if (expectedResult) {
            assertTrue("Ожидается переход на страницу входа",
                    loginPage.isLoginButtonDisplayed());
        } else {
            assertTrue("Ожидается, что пользователь остается на странице регистрации",
                    registrationPage.isPasswordErrorDisplayed());
        }
    }

    @After
    public void tearDown() {
        try {
            if (expectedResult) {
                deleteUser(email, password);
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя из-за таймаута сервера.");
        }
    }
}