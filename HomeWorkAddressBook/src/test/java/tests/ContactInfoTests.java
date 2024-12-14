package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testContactData() {
        var contacts = app.hbm().getContactList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var expectedEmails = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var expectedAddress = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var phones = app.records().getContactPhones();
        var emails = app.records().getEmails();
        var address = app.records().getAddress();
        Assertions.assertEquals(expectedPhones, phones);
        Assertions.assertEquals(expectedEmails, emails);
        Assertions.assertEquals(expectedAddress, address);
    }




}
