package site.nomoreparties.stellarburgers.utils;

import java.util.UUID;

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
}