package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void type(By locator, String text) {
        manager.driver.findElement(locator);
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

}