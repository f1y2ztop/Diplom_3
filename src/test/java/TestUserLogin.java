import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.util.DataForTesting;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestUserLogin extends BaseTest{

    private final String entryName;
    private final String startUrl;
    private final By entryButtonLocator;

    private final String email = "yandex" + System.currentTimeMillis() + "@yandex.ru";
    private final String password = "validPassword";
    private final String name = "Алексей";

    public TestUserLogin(String entryName, String startUrl, By entryButtonLocator) {
        this.startUrl = startUrl;
        this.entryButtonLocator = entryButtonLocator;
        this.entryName = entryName;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters
    public static Collection<Object[]> getLogin() {
        return Arrays.asList(DataForTesting.loginEntranceData);
    }

    @Before
            public void setUp() throws InterruptedException {
        userSetUp(email);
    }

    @Test
    @DisplayName("Логин пользователя")
    public void testLoginDiffButtons() {
        WebDriver driver = factory.getDriver();
        driver.get(startUrl);
        driver.findElement(entryButtonLocator).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailLoginField(email);
        loginPage.fillPasswordLoginPage(password);
        loginPage.loginButtonLoginPageClick();
    }

    @After
    public void tearDown() {
        try {deleteUser(email, password);}
        catch(Exception e){
            System.out.println("Не удалось удалить пользователя из-за таймаута сервера.");
        }
    }
}