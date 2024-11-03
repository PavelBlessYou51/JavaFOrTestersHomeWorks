package manager;

import model.RecordData;
import org.openqa.selenium.By;

public class RecordHelper extends HelperBase {

    public RecordHelper(ApplicationManager manager) {
        super(manager);
    }


    public void addNewRecord(RecordData record) {
        initRecordCreation();
        fillRecordForm(record);
        submitRecordCreation();
        returnToHomePage();
    }

    public void removeRecord() {
        selectRecord();
        submitRecordRemove();
    }

    private void submitRecordRemove() {
        click(By.cssSelector("input[value='Delete']"));
    }

    private void selectRecord() {
        click(By.name("selected[]"));
    }

    private void initRecordCreation() {
        click(By.linkText("add new"));
    }

    private void fillRecordForm(RecordData record) {
        type(By.name("firstname"), record.firstName());
        type(By.name("lastname"), record.lastName());
    }

    private void submitRecordCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isRecordPresent(By locator) {
        return manager.isElementPresent(locator);
    }


}

