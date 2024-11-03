package tests;

import model.RecordData;
import org.junit.jupiter.api.Test;

public class AddRecordTests extends TestBase {


    @Test
    public void canCreateRecord() {
        app.records().addNewRecord(new RecordData());
    }
}
