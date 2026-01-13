import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import ru.yandex.practicum.models.User;
import ru.yandex.practicum.models.UserApi;
import ru.yandex.practicum.util.EnvConfig;

public class BaseTest {
    protected UserApi userApi;

    @Before
    public void startUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(EnvConfig.MAIN_URL)
                .setContentType(ContentType.JSON)
                .build();
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
        userApi = new UserApi();
    }

    @Step("Создание пользователя через API: {name}, {email}")
    protected void userSetUp(String email) {
        User user = new User(email, "validPassword", "Алексей");
        userApi.createUser(user);
    }

    @Step("Удаление пользователя через API: {email}")
    protected void cleanUpUser(String email, String password) {
        try {
            User user = new User(email, password);
            Response response = userApi.login(user);

            if (response.getStatusCode() == 200) {
                String token = response.path("accessToken");
                userApi.deleteUser(token);
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя: " + e.getMessage());
        }
    }
}
