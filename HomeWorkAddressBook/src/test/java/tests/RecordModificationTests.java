package tests;

import model.GroupData;
import model.RecordData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class RecordModificationTests extends TestBase {

    @Test
    void canModifyRecord() {
        if (app.records().getRecordCounts() == 0) {
            app.groups().createGroup(new GroupData());
        }
        List<RecordData> oldRecords = app.records().getList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldRecords.size());
        RecordData testData = new RecordData().withLastName("Modified last name");
        app.records().modifyRecord(oldRecords.get(index), testData);
        List<RecordData> newRecords = app.records().getList();
        ArrayList<RecordData> expectedList = new ArrayList<>(oldRecords);
        expectedList.set(index, testData.withId(oldRecords.get(index).id()));
        Comparator<RecordData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRecords.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRecords, expectedList);

    }
}
