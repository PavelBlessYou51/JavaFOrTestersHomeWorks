package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class CreateGroupTests extends TestBase {


    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData().withFooter("some footer"));
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }


}
