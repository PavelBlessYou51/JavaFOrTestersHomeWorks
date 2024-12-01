package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreateGroupTests extends TestBase {


    public static List<GroupData> groupProvider() throws IOException {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (String name : List.of("", "group_name")) {
            for (String header : List.of("", "group_header")) {
                for (String footer : List.of("", "group_footer")) {
                    groups.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<GroupData> value;
        //        ObjectMapper mapper = new YAMLMapper();
        //        XmlMapper mapper = new XmlMapper();
        if (new File("HomeWorkAddressBook/group.json").exists()) {
            //        String json = Files.readString((Paths.get("group.json")));
            value = mapper.readValue(new File("group.json"), new TypeReference<ArrayList<GroupData>>() {
            }); // можно заменить кодом выше, передав json
            //        ArrayList<GroupData> value = mapper.readValue(new File("group.yaml"), new TypeReference<ArrayList<GroupData>>() {});
            //        ArrayList<GroupData> value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {});
        } else {
            value = mapper.readValue(new File("records.json"), new TypeReference<ArrayList<GroupData>>() {
            });
        }
        groups.addAll(value);
        return groups;
    }

    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30)));
    }

    public static List<GroupData> negativeGroupProvider() {
        ArrayList<GroupData> groups = new ArrayList<>();
        groups.add(new GroupData().withId("").withName("group_name'").withHeader("").withHeader(""));
        return groups;
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.hbm().createGroup(new GroupData(group.id(), group.name(), group.header(), group.footer()));
        List<GroupData> newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        String maxId = newGroups.get(newGroups.size() - 1).id();
        ArrayList<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

        List<GroupData> newUiGroups = app.groups().getList();
        newUiGroups.sort(compareById);
        Assertions.assertEquals(newGroups, newUiGroups);

    }


    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        List<GroupData> newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

}
