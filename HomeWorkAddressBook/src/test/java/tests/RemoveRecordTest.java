package tests;

import model.RecordData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemoveRecordTest extends TestBase {

    @Test
    public void canRemoveRecord() {
        if (app.records().getRecordCounts() == 0) {
            app.records().addNewRecord(new RecordData());
        }
        ArrayList<RecordData> oldRecords = app.records().getList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldRecords.size());
        app.records().removeRecord(oldRecords.get(index));
        app.records().returnToHomePage();
        ArrayList<RecordData> newRecords = app.records().getList();
        ArrayList<RecordData> expetedList = new ArrayList<>(oldRecords);
        expetedList.remove(index);
        Assertions.assertNotEquals(newRecords, expetedList);
    }

    @Test
    public void removeAllRecord() {
        if (app.records().getRecordCounts() == 0) {
            app.records().addNewRecord(new RecordData());
        }
        app.records().removeAllRecord();
        Assertions.assertNotEquals(0, app.records().getRecordCounts());
    }
}
