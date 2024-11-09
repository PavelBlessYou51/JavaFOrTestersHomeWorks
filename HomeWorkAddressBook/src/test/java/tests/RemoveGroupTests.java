package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemoveGroupTests extends TestBase {


    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData());
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expetedList = new ArrayList<>(oldGroups);
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
