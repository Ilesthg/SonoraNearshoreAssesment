package base;

import enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseDriver {
    protected BaseDriver() {
    }

    protected WebDriver driver;
    protected WebDriverWait wait;


    @BeforeClass
    protected void initDriver() {
         driver = DriverFactory.getLocalDriver(Browsers.CHROME);
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        //this.driver.get("https://the-internet.herokuapp.com/dynamic_content");
        this.driver.manage().window().maximize();

    }
 /*    @AfterMethod
     protected void closeDriver() {
         this.driver.quit();

     }
*/


}
