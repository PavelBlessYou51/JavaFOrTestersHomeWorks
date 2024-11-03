package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class RemoveGroupTests extends TestBase {


    @Test
    public void removeGroupTest() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData());
        }
        app.groups().removeGroup();

    }


}
