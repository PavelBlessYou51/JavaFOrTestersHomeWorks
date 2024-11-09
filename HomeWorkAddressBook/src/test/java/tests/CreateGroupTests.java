package tests;

import model.GroupData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupTests extends TestBase {


    public static List<GroupData> groupProvider() {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (String name : List.of("", "group_name")) {
            for (String header : List.of("", "group_header")) {
                for (String footer : List.of("", "group_footer")) {
                    groups.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            groups.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10))
            );
        }
        return groups;
    }

    public static List<GroupData> negativeGroupProvider() {
        ArrayList<GroupData> groups = new ArrayList<>();
        groups.add(new GroupData().withId("").withName("group_name'").withHeader("").withHeader(""));
        return groups;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultiplyGroups(GroupData group) {
        app.groups().createGroup(group);
    }


    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        app.groups().createGroup(group);
    }

}
