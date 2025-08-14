package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeManager {
    private EdgeManager() {
    }

    ;

    public static WebDriver edgeManagerLocal() {
        try {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Local ChromeDriver: " + e.getMessage(), e);
        }
    }
    public static WebDriver edgeManagerLocal(EdgeOptions options) {
        try {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Local ChromeDriver: " + e.getMessage(), e);
        }
    }

}
