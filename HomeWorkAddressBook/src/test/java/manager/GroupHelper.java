package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(modifiedGroup);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

//    public ArrayList<GroupData> getList() {
//        openGroupsPage();
//        ArrayList<GroupData> groups = new ArrayList<>();
//        List<WebElement> spans = manager.driver.findElements(By.cssSelector("span[class='group']"));
//
//
//    }


    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }



    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }


    public ArrayList<GroupData> getList() {
        openGroupsPage();
        ArrayList<GroupData> groups = new ArrayList<>();
        List<WebElement> spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (WebElement span : spans) {
            String name = span.getText();
            WebElement checkbox = manager.driver.findElement(By.name("selected[]"));
            String id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    private void removeSelectedGroup() {
        click(By.name("delete"));
    }


    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }
}
