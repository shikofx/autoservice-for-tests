package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    Properties currentProperties = new Properties();

    Properties getCurrentProperties() throws IOException {
        FileInputStream
            inputStream =
            new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/mobile-driver.properties");
        currentProperties.load(inputStream);
        inputStream.close();
        return currentProperties;
    }

    protected String getProperties(String propertyKey) throws IOException {
        if (!currentProperties.containsKey(propertyKey)) {
            currentProperties = getCurrentProperties();
        }
        return currentProperties.getProperty(String.valueOf(propertyKey), null);
    }

}
