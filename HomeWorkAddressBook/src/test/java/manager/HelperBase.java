package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;
import java.util.Random;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void type(By locator, String text) {
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }


    protected int getRandomInt(int ceil) {
        Random rand = new Random();
        return rand.nextInt(ceil);
    }
}
