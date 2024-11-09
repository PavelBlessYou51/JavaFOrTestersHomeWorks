package tests;

import model.RecordData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class AddRecordTests extends TestBase {

    public static List<RecordData> recordProvider() {
        ArrayList<RecordData> records = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            records.add(new RecordData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
            );
        }
        return records;
    }


    @ParameterizedTest
    @MethodSource("recordProvider")
    public void canCreateRecord(RecordData record) {
        app.records().addNewRecord(record);
    }
}
