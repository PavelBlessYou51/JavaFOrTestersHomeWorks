package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondary(), contact.getEmail1(), contact.getEmail2(), contact.getEmail3(), contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var phones = app.records().getContactData();
        Assertions.assertEquals(expected, phones);

    }




}
