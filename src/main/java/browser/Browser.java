package browser;

import java.io.FileInputStream;
import java.io.IOException;
import com.codeborne.selenide.Configuration;
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
        Configuration.browserCapabilities.setCapability("enableVNC", true); // Включить VNC (если используется Selenoid)
        Configuration.browserCapabilities.setCapability("enableVideo", true); // Включить запись видео (если используется Selenoid)
    }



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