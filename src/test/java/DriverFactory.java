import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.Collections;

public class DriverFactory extends ExternalResource {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        Allure.getLifecycle().updateTestCase(result -> {
            String oldName = result.getName();
            result.setName(oldName + " [" + browser.toUpperCase() + "]");

            result.setHistoryId(result.getHistoryId() + browser);
            result.setParameters(Collections.singletonList(
                    new Parameter().setName("Browser").setValue(browser)));
        });
        if ("yandex".equals(System.getProperty("browser"))) {
            startYandex();
        } else {
            startChrome();
        }
    }

    private void startYandex() {
        System.setProperty("webdriver.chrome.driver", "yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        String yandexPath = System.getenv("LOCALAPPDATA") + "\\Yandex\\YandexBrowser\\Application\\browser.exe";
        options.setBinary(yandexPath);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    private void startChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
