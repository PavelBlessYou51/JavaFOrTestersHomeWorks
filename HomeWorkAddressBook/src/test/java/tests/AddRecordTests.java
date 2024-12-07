package tests;

import common.CommonFunctions;
import model.GroupData;
import model.RecordData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddRecordTests extends TestBase {

    public static List<RecordData> recordProvider() {
        ArrayList<RecordData> records = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            records.add(new RecordData()
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withLastName(CommonFunctions.randomString(i * 10))
                    .withPhoto(randomFile("src/test/resources/images"))
            );
        }
        return records;
    }


    @ParameterizedTest
    @MethodSource("recordProvider")
    public void canCreateMultiplyRecord(RecordData record) {
        List<RecordData> oldRecords = app.hbm().getContactList();
        app.records().addNewRecord(record);
        List<RecordData> newRecords = app.hbm().getContactList();
        Comparator<RecordData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRecords.sort(compareById);
        ArrayList<RecordData> expectedList = new ArrayList<>(oldRecords);
        RecordData recordToAdd = newRecords.get(newRecords.size() - 1);
        expectedList.add(record.withId(recordToAdd.id()).withLastName(recordToAdd.lastName()).withFirstName(recordToAdd.firstName()).withPhoto(recordToAdd.photo()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRecords, expectedList);

        app.records().addNewRecord(record);

    }

    @Test
    void canCreateRecordInGroup() {
        RecordData record = new RecordData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.records().createNewRecord(record, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    void AddRecordInGroup() {
        RecordData record = new RecordData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        List<RecordData> contacts = app.hbm().getContactList();
        app.records().selectRandomRecord(contacts);
        List<GroupData> groups = app.hbm().getGroupList();
        var groupToAdd = app.groups().selectRandomGroup(groups);
        var oldRelated = app.hbm().getContactsInGroup(groupToAdd);
        app.records().addRecordToGroup();
        var newRelated = app.hbm().getContactsInGroup(groupToAdd);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }


}


