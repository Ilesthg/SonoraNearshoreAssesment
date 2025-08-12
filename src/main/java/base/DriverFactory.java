package base;

import enums.Browsers;
import utilities.ChromeManager;
import utilities.EdgeManager;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.function.Supplier;

public class DriverFactory {

    public static WebDriver getLocalDriver(Browsers browser) {//String browser, Object[] data

        try {
            HashMap<Browsers, Supplier<WebDriver>> hmm = new HashMap<>();

            hmm.put(Browsers.CHROME, ChromeManager::chromeManagerLocal);
            hmm.put(Browsers.EDGE, EdgeManager::edgeManagerLocal);
            // hmm.put(BrowserType.FIREFOX, return new FirefoxDriver());

            return hmm.get(browser).get();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }
}
