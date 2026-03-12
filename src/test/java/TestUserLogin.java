import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.util.DataForTesting;
import ru.yandex.practicum.util.LoginSteps;
import ru.yandex.practicum.util.LoginType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestUserLogin extends BaseTest {

    private final String description;
    private final LoginType loginType;

    private final String email = "yandex" + System.currentTimeMillis() + "@yandex.ru";
    private final String password = "validPassword";

    public TestUserLogin(String description, LoginType loginType) {
        this.description = description;
        this.loginType = loginType;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters
    public static Collection<Object[]> getLogin() {
        return Arrays.asList(DataForTesting.loginEntranceData);
    }

    @Before
    public void setUp() {
        userSetUp(email);
    }

    @Test
    @DisplayName("Логин пользователя")
    @Description("Проверка возможности входа в систему через различные точки входа: " +
            "кнопка 'Войти в аккаунт' на главной, через 'Личный кабинет', " +
            "со страницы регистрации и со страницы восстановления пароля")
    public void testLoginDiffButtons() {
        WebDriver driver = factory.getDriver();
        LoginSteps loginSteps = new LoginSteps(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        loginSteps.navigateToLogin(loginType);

        loginPage.fillEmailLoginField(email);
        loginPage.fillPasswordLoginPage(password);
        loginPage.loginButtonLoginPageClick();
        assertTrue("Кнопка 'Оформить заказ' должна быть видна после успешного входа",
                mainPage.isOrderButtonVisible());
    }

    @After
    public void tearDown() {
        try {
            if (factory.getDriver() != null) {
                factory.getDriver().quit();
            }
        } finally {
            // Очистка юзера пойдет после закрытия браузера
            cleanUpUser(email, password);
        }
    }
}