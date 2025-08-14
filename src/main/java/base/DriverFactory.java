package base;

import enums.Browsers;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import utilities.ChromeManager;
import utilities.EdgeManager;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class DriverFactory {

    public static WebDriver getLocalDriver(Browsers browser) {

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

    public static WebDriver getLocalDriver(Browsers browser, Object options) {
        try {
            HashMap<Browsers, Function<Object, WebDriver>> hmm = new HashMap<>();

            hmm.put(Browsers.CHROME, opt -> ChromeManager.chromeManagerLocal((ChromeOptions) opt));
            hmm.put(Browsers.EDGE, opt -> EdgeManager.edgeManagerLocal((EdgeOptions) opt));

            return hmm.get(browser).apply(options);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }
}
