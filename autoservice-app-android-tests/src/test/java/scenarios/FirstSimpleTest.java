package scenarios;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import setup.MobileProperties;

import java.io.IOException;
import java.net.MalformedURLException;

public class FirstSimpleTest extends DriverSetup {

    public FirstSimpleTest() throws IOException {
        super();
    }

    @BeforeClass
    public static void setUp() throws MalformedURLException {

    }

    @Test
    public void simplestTest() throws MalformedURLException {
        prepareAndroidAppDriver();
        String appPackageName = "ru.descbook.polyglotfree:id/";
        By addPlayerButton = By.id(appPackageName + "mainMenuButtonAddPlayer");
        driver.findElement(addPlayerButton).click();
        driver.closeApp();
    }

    @Test
    public void simplestWebTest() throws IOException {
        prepafeAndroidWebDriver();
        MobileProperties mobileProperties = new MobileProperties();
        webDriver.get(mobileProperties.APPLICATION_URL);
        webDriver.closeApp();
    }

    @AfterClass
    public static void tearDown() {

    }

}
