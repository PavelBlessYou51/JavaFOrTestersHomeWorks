package tests;

import model.RecordData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class RemoveRecordTest extends TestBase{

    @Test
    public void canRemoveRecord() {
        if (!app.records().isRecordPresent(By.name("selected[]"))) {
            app.records().addNewRecord(new RecordData());
        }
        app.records().removeRecord();
    }
}
