package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.IssueData;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase{

    DeveloperMailUser user;

    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        app.JamesCli().addUser(email, "password");
        app.session().createNewAcc(username, email);
        app.session().proceed();
        var messanges = app.mail().receive(email, "password", Duration.ofSeconds(10));
        var url = app.mail().getUrl(messanges);
        app.driver().get(url);
        app.session().changePass(username, "password");
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }

    @Test
    void canRegisterUserWithAPI() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        app.rest().registerUser(password, email, user.name());
        var messange = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = app.mail().getUrlFromDeveloperMail(messange);
        app.driver().get(url);
        app.session().changePass(user.name(), password);
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
