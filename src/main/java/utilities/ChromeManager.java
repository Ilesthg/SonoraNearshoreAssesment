package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeManager {
    private ChromeManager() {
    }

    public static WebDriver chromeManagerLocal() {
        try {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Local ChromeDriver: " + e.getMessage(), e);
        }
    }

}
