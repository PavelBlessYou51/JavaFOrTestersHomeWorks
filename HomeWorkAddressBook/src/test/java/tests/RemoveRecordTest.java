package tests;

import common.CommonFunctions;
import model.GroupData;
import model.RecordData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveRecordTest extends TestBase {

    @Test
    public void canRemoveRecord() {
        if (app.hbm().getContactCount() == 0) {
            app.records().addNewRecord(new RecordData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        List<RecordData> oldRecords = app.hbm().getContactList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldRecords.size());
        app.records().removeRecord(oldRecords.get(index));
        app.records().returnToHomePage();
        List<RecordData> newRecords = app.hbm().getContactList();
        ArrayList<RecordData> expetedList = new ArrayList<>(oldRecords);
        expetedList.remove(index);
        Assertions.assertEquals(newRecords, expetedList);
    }

    @Test
    public void removeAllRecord() {
        if (app.hbm().getContactCount() == 0) {
            app.records().addNewRecord(new RecordData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        app.records().removeAllRecord();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

    @Test
    void removeRecordFromGroup() {
        if (app.hbm().getGroupCount() == 0 || app.hbm().getContactCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            RecordData record = new RecordData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"));
            var group = app.hbm().getGroupList().get(0);
            app.records().createNewRecord(record, group);
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.records().removeContact(oldRelated.get(0), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size(), newRelated.size() + 1);
    }
}
