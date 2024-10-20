import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RemoveGroupTests {
    private static WebDriver driver;


    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.get("http://localhost/addressbook/");
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.manage().window().setSize(new Dimension(1324, 835));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }

    }

    @AfterEach
    public void tearDown() {
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @Test
    public void removeGroupTest() {
        if (!isElementPrersnt(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
        if (!isElementPrersnt(By.name("selected[]"))) {
            driver.findElement(By.name("new")).click();
            driver.findElement(By.name("group_name")).sendKeys("Name");
            driver.findElement(By.name("group_header")).sendKeys("Header");
            driver.findElement(By.name("group_footer")).sendKeys("Footer");
            driver.findElement(By.name("submit")).click();
        }
        driver.findElement(By.linkText("groups")).click();
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();

    }

    private boolean isElementPrersnt(By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
