import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.util.DataForTesting;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestConstructorSwitchSections {

    public final String sectionName;

    public TestConstructorSwitchSections(String sectionName) {
        this.sectionName = sectionName;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters(name = "Название раздела: {0}")
    public static Collection<Object[]> getConstructorData() {
        return Arrays.asList(DataForTesting.constructorSectionsData);
    }

    @Test
    @DisplayName("Проверка перехода к разделам")
    public void testConstructorSwitchSections() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        if (sectionName.equals("Булки") || sectionName.equals("Соусы")) {
            mainPage.sectionClick("Начинки");
        }
        mainPage.sectionClick(sectionName);
        assertTrue("Заголовок раздела" + sectionName + " должен быть виден",
                mainPage.isSectionVisible(sectionName));
    }
}
