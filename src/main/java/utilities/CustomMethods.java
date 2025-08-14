package utilities;

import base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomMethods  {
private WebDriver driver;
    private static final By TITLE = By.xpath("//h3");

public CustomMethods(WebDriver driver) {
        this.driver = driver;
    }
    public void navigateTo(String url, String expectedTitle) {
        driver.get(url);
        System.out.println(driver.findElement(TITLE).getText());
        if (!driver.findElement(TITLE).getText().equals(expectedTitle)) {
            throw new IllegalStateException("This is not the"+ expectedTitle +"page");
        }
    }
    public void getTitlePage() {

        // This method will be used to get the title of the page
        // Implementation will be added later

    }
}
