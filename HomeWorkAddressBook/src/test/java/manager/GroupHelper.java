package manager;

import io.qameta.allure.Step;
import model.GroupData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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

    @Step
    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
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


    public List<GroupData> getList() {
        openGroupsPage();
        ArrayList<GroupData> groups = new ArrayList<>();
        int spanCount = manager.driver.findElements(By.cssSelector("span.group")).size();
        for (int i = 0; i < spanCount; i++) {
            List<WebElement> spans = manager.driver.findElements(By.cssSelector("span.group"));
            String name = spans.get(i).getText();
            WebElement checkbox = spans.get(i).findElement(By.name("selected[]"));
            String id = checkbox.getAttribute("value");
            checkbox.click();
            initGroupModification();
            String header = manager.driver.findElement(By.cssSelector("textarea[name='group_header']")).getText();
            String footer = manager.driver.findElement(By.cssSelector("textarea[name='group_footer']")).getText();
            openGroupsPage();
            groups.add(new GroupData().withId(id).withName(name).withHeader(header).withFooter(footer));
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

    public GroupData selectRandomGroup(List<GroupData> groups) {
//        manager.driver.navigate().refresh();
//        Alert alert = manager.driver.switchTo().alert();
//        alert.accept();
        GroupData randomGroup = groups.get(getRandomInt(groups.size()));
        Select toGroup = new Select(manager.driver.findElement(By.cssSelector("select[name='to_group']")));
        toGroup.selectByValue(randomGroup.id());
        return randomGroup;
    }

    public List<WebElement> getContactsWithoutGroup() {
        showContactsWithoutGroup();
        List<WebElement> contactsList = manager.driver.findElements(By.name("selected[]"));
        return contactsList;


    }

    public void showContactsWithoutGroup() {
        Select typeContacts = new Select(manager.driver.findElement(By.cssSelector("select[name='group']")));
        typeContacts.selectByValue("[none]");
    }
}
