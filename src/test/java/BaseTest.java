import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import ru.yandex.practicum.util.EnvConfig;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;

public class BaseTest {
    @Before
    public void startUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(EnvConfig.MAIN_URL)
                .setContentType(ContentType.JSON)
                .build();
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }
    @Step("Создание пользователя через API: {name}, {email}")
    protected void userSetUp(String email) throws InterruptedException {
        Thread.sleep(3000);
        String body = String.format(
                "{\"name\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
                "Алексей", email, "validPassword");

        given()
                .body(body)
                .when()
                .post("api/auth/register");
        Thread.sleep(3000);
    }
    @Step("Удаление пользователя через API: {email}")
    protected void deleteUser(String email, String password) throws InterruptedException {
        String data = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        Response response = given()
                .body(data)
                .when()
                .post("api/auth/login");

        if (response.getStatusCode() == SC_OK) {
            String token = response.path("accessToken");

            given()
                    .header("Authorization", token)
                    .when()
                    .delete("api/auth/user")
                    .then()
                    .statusCode(SC_ACCEPTED);
            Thread.sleep(5000);
        }
    }
}
