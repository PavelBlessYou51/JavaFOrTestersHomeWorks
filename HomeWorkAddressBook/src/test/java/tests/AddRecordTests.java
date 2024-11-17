package tests;

import common.CommonFunctions;
import model.RecordData;
import org.junit.jupiter.api.Assertions;
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
        ArrayList<RecordData> oldRecords = app.records().getList();
        app.records().addNewRecord(record);
        ArrayList<RecordData> newRecords = app.records().getList();
        Comparator<RecordData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRecords.sort(compareById);
        ArrayList<RecordData> expectedList = new ArrayList<>(oldRecords);
        RecordData recordToAdd = newRecords.get(newRecords.size() - 1);
        expectedList.add(record.withId(recordToAdd.id()).withLastName(recordToAdd.lastName()).withFirstName(recordToAdd.firstName()).withPhoto(recordToAdd.photo()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRecords, expectedList);

    }
}
