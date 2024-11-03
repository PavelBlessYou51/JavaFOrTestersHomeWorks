package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init();
    }

//    @AfterEach
//    public void tearDown() {
//        app.driver.findElement(By.linkText("Logout")).click();
//        app.driver.quit();
//    }

}
