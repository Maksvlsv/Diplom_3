package site.nomoreparties.stellarburgers.utils;

import io.restassured.RestAssured;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class UserGenerator {

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
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}")
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200);
    }

    public static String createRandomUser() {
        String email = generateRandomEmail();
        createUserViaApi(email, getDefaultPassword(), getDefaultName());
        return email;
    }
}