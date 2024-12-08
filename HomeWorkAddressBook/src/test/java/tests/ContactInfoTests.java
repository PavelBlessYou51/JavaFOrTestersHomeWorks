package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var phones = app.records().getPhones();
        Assertions.assertEquals(expected, phones);

    }

    @Test
    void testEmails() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var emails = app.records().getEmails();
        Assertions.assertEquals(expected, emails);

    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var addreses = app.records().getAddress();
        Assertions.assertEquals(expected, addreses);

    }




}
