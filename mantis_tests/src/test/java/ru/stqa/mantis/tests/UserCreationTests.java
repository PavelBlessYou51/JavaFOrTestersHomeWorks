package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{

    DeveloperMailUser user;

//    public static Stream<String> randomUser() {
//        return Stream.of(CommonFunctions.randomString(8));
//    }

    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        app.session().createNewAcc(user.name(), email);
        app.session().proceed();
        var messange = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = app.mail().getUrlFromDeveloperMail(messange);
        app.driver().get(url);
        app.session().changePass(user.name(), password);
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
