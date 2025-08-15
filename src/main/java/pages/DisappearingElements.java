package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.CustomMethods;

import java.time.Duration;


public class DisappearingElements {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/disappearing_elements";
    private static final String PAGE_TITLE = "Disappearing Elements";
    private static final By GALLERY_BUTTON = By.linkText("Gallery");
    private static final int MAX_REFRESH_ATTEMPTS = 10;
    private CustomMethods customMethods;


    public DisappearingElements(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.customMethods = new CustomMethods(this.driver);
    }

    public DisappearingElements navigateToPage() {
        customMethods.openPageAndValidateTitle(PAGE_URL, PAGE_TITLE);
        return this;
    }

    public void refreshUntilElementExists() {
        int attempts = 0;
        boolean elementFound = false;

        while (attempts < MAX_REFRESH_ATTEMPTS && !elementFound) {
            try {
                // Check if element exists without waiting
                driver.findElement(GALLERY_BUTTON);
                elementFound = true;
                System.out.println("Gallery button found after " + (attempts) + " attempts");
            } catch (NoSuchElementException e) {
                attempts++;
                System.out.println("Attempt " + attempts + ": Gallery button not found, refreshing");
                if (attempts < MAX_REFRESH_ATTEMPTS) {
                    driver.navigate().refresh();
                    waitForPageToLoad(Duration.ofSeconds(2));
                }
            }
        }

        if (!elementFound) {
            Assert.fail("Gallery button did not appear after " + MAX_REFRESH_ATTEMPTS + " refresh attempts");
        }
    }


    public void refreshUntilElementNotExists() {
        int attempts = 0;
        boolean elementGone = false;

        while (attempts < MAX_REFRESH_ATTEMPTS && !elementGone) {
            try {
                // Check if element still exists
                driver.findElement(GALLERY_BUTTON);
                attempts++;
                System.out.println("Attempt " + attempts + ": Gallery button still exists, refreshing...");
                if (attempts < MAX_REFRESH_ATTEMPTS) {
                    driver.navigate().refresh();
                    waitForPageToLoad(Duration.ofSeconds(2));
                }
            } catch (NoSuchElementException e) {
                elementGone = true;
                System.out.println("Gallery button disappeared after " + (attempts) + " attempt(s)");
            }
        }

        if (!elementGone) {
            Assert.fail("Gallery button did not disappear after " + MAX_REFRESH_ATTEMPTS + " refresh attempts");
        }
    }

    public boolean isElementPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(GALLERY_BUTTON));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void waitForPageToLoad(Duration timeout) {

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {

                JavascriptExecutor js = (JavascriptExecutor) driver;
                String readyState = (String) js.executeScript("return document.readyState");

                return "complete".equals(readyState);
            }
        });
    }


}
