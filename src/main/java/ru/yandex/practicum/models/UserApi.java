package ru.yandex.practicum.models;

import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;

public class UserApi {
    private static final String REGISTER_PATH = "api/auth/register";
    private static final String LOGIN_PATH = "api/auth/login";
    private static final String USER_PATH = "api/auth/user";

    @Step("Создание пользователя")
    public void createUser(User user) {
         given()
                .body(user, ObjectMapperType.GSON)
                .when()
                .post(REGISTER_PATH)
                .then()
                .statusCode(SC_OK);
    }

    @Step("Логин пользователя")
    public Response login(User user) {
        return given()
                .body(user, ObjectMapperType.GSON)
                .when()
                .post(LOGIN_PATH);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete(USER_PATH)
                .then()
                .statusCode(SC_ACCEPTED);
    }
}

