import io.qameta.allure.Description;
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
    @Description("Проверяет, что при клике на вкладки 'Булки', 'Соусы' и 'Начинки'" +
            "происходит переход к соответствующему разделу конструктора")
    public void testConstructorSwitchSections() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        switch (sectionName) {
            case "Соусы":
                mainPage.clickSaucesSection();
                assertTrue("Вкладка 'Соусы' не активна",
                        mainPage.isTabActive(mainPage.getSaucesTab()));
                break;

            case "Начинки":
                mainPage.clickFillingsSection();
                assertTrue("Вкладка 'Начинки' не активна",
                        mainPage.isTabActive(mainPage.getFillingsTab()));
                break;

            case "Булки":
                mainPage.clickFillingsSection();
                mainPage.clickBunsSection();
                assertTrue("Вкладка 'Булки' не активна",
                        mainPage.isTabActive(mainPage.getBunsTab()));
                break;
        }
    }
}