package scenarios;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import setup.MobileProperties;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    private MobileProperties mobileProperties;
    protected static AndroidDriver driver;
    protected static AndroidDriver webDriver;

    public DriverSetup() throws IOException {
        mobileProperties = new MobileProperties();
    }

    protected void prepareAndroidAppDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("device", "Android");
        capabilities.setCapability("deviceName", mobileProperties.DEVICE_NAME);
        capabilities.setCapability("platformName", mobileProperties.MOBILE_PLATFORM);

        File app = new File(mobileProperties.APPLICATION_PATH);
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL(mobileProperties.APPIUM_SERVER), capabilities);
    }

    protected void prepafeAndroidWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("device", "Android");
        capabilities.setCapability("deviceName", mobileProperties.DEVICE_NAME);
        capabilities.setCapability("platformName", mobileProperties.MOBILE_PLATFORM);

        capabilities.setCapability("browserName", mobileProperties.BROWSER_NAME);
        webDriver = new AndroidDriver(new URL(mobileProperties.APPIUM_SERVER), capabilities);
    }

}
