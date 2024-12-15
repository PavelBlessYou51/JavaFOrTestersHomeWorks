package ru.stqa.mantis.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

    @Test
    void canReceiveEmail() {
        var messanges = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messanges.size());
        System.out.println(messanges);
    }

    @Test
    void canExtractUrl() {
        var messanges = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(60));
        var text = messanges.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }

    }

    @Test
    void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }
}
