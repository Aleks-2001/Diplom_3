package browser;

import java.io.FileInputStream;
import java.io.IOException;
import com.codeborne.selenide.Configuration;
import java.util.Properties;

public class Browser {

    public static void initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");

        BrowserType browserType = BrowserType.valueOf (browserProperty);
        switch (browserType) {
            case CHROME:
                Configuration.browser = "CHROME";
                break;
            case YANDEX:
                Configuration.browser = "YANDEX";
                break;
            case SAFARI:
                Configuration.browser = "SAFARI";
                break;
            default:
                throw new RuntimeException("Browser undefined");
        }

    }

}