package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveGroupTests extends TestBase {


    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData());
        }
        List<GroupData> oldGroups = app.groups().getList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        List<GroupData> newGroups = app.groups().getList();
        ArrayList<GroupData> expetedList = new ArrayList<>(oldGroups);
        expetedList.remove(index);
        Assertions.assertEquals(newGroups, expetedList);

    }

    @Test
    public void removeAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData());
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }


}
