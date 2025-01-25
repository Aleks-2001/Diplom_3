package browser;

import java.io.FileInputStream;
import java.io.IOException;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class Browser {

    public static void initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));

        String browserProperty = properties.getProperty("testBrowser"); // Получаем имя браузера
        String remoteUrl = properties.getProperty("remoteUrl"); // Получаем URL для Selenium Grid

        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl; // Устанавливаем RemoteWebDriver
        }

        BrowserType browserType = BrowserType.valueOf(browserProperty.toUpperCase());
        switch (browserType) {
            case CHROME:
                Configuration.browser = "chrome"; // Указываем браузер Chrome
                break;
            case YANDEX:
                Configuration.browser = "yandex";
                break;
            case SAFARI:
                Configuration.browser = "safari";
                break;
            default:
                throw new RuntimeException("Browser undefined");
        }

        // Дополнительные настройки браузера
//        Configuration.browserCapabilities.setCapability("browserName", "chrome");
//        Configuration.browserCapabilities.setCapability("goog:chromeOptions", chromeOptions());

    }

//    private ChromeOptions chromeOptions() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--window-size=1366,768");
//        options.addArguments("--proxy-bypass-list=<-loopback>");
//        return options;
//    }




//    public static void initDriver() throws IOException {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("src/test/resources/browser.properties"));
//        String browserProperty = properties.getProperty("testBrowser");
//
//        BrowserType browserType = BrowserType.valueOf (browserProperty);
//        switch (browserType) {
//            case CHROME:
//                Configuration.browser = "CHROME";
//                break;
//            case YANDEX:
//                Configuration.browser = "YANDEX";
//                break;
//            case SAFARI:
//                Configuration.browser = "SAFARI";
//                break;
//            default:
//                throw new RuntimeException("Browser undefined");
//        }
//
//    }

}