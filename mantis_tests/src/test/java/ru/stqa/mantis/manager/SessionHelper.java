package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper (ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void createNewAcc(String username, String email) {
        click(By.cssSelector("a[class='back-to-login-link pull-left']"));
        type(By.id("username"), username);
        type(By.id("email-field"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void proceed() {
        click(By.cssSelector("a[href='login_page.php']"));
    }

    public void changePass(String username, String password) {
        type(By.id("realname"), username);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
