package site.nomoreparties.stellarburgers.utils;

import io.restassured.RestAssured;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class UserGenerator {

    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    public static String generateRandomEmail() {
        String uuidPart = UUID.randomUUID().toString().substring(0, 8);
        return "user_" + uuidPart + "@yandex.ru";
    }

    public static String getDefaultPassword() {
        return "123456";
    }

    public static String getShortPassword() {
        return "123";
    }

    public static String getDefaultName() {
        return "Тест";
    }

    public static void createUserViaApi(String email, String password, String name) {
        RestAssured.baseURI = BASE_URI;

        given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}")
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200);
    }

    public static String getAccessToken(String email, String password) {
        RestAssured.baseURI = BASE_URI;

        return given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    public static void deleteUser(String token) {
        RestAssured.baseURI = BASE_URI;

        given()
                .header("Authorization", token)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }

    public static String createRandomUser() {
        String email = generateRandomEmail();
        createUserViaApi(email, getDefaultPassword(), getDefaultName());
        return email;
    }
}