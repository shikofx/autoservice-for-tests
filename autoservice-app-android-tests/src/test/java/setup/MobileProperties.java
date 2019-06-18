package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class MobileProperties extends TestProperties {

    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities;
    protected WebDriverWait wait;
    public String APPLICATION_URL;
    public String MOBILE_PLATFORM;
    public String APPLICATION_PATH;
    public String APPIUM_SERVER;
    public String BROWSER_NAME;
    public String DEVICE_NAME;

    public MobileProperties() throws IOException {
        MOBILE_PLATFORM = getProperties("mobilePlatform");
        APPLICATION_PATH = getProperties("applicationPath");
        APPIUM_SERVER = getProperties("appiumServer");
        BROWSER_NAME = getProperties("browserName");
        DEVICE_NAME = getProperties("deviceName");
        APPLICATION_URL = getProperties("applicationUrl");
    }
}
