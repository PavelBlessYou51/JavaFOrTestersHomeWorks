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
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        List<GroupData> oldGroups = app.hbm().getGroupList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        List<GroupData> newGroups = app.hbm().getGroupList();
        ArrayList<GroupData> expetedList = new ArrayList<>(oldGroups);
        expetedList.remove(index);
        Assertions.assertEquals(newGroups, expetedList);

    }

    @Test
    public void removeAllGroupsAtOnce() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }


}
