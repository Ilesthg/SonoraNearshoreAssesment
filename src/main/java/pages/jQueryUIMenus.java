package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class jQueryUIMenus {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private String downloadPath;

    // Page URL
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/jqueryui/menu";
    static String downloadFolder = System.getProperty("user.home") + "/Downloads";
    // Locators for jQuery UI Menu
    private static final By ENABLED_MENU = By.xpath("//a[text()='Enabled']");
    private static final By DOWNLOADS_OPTION = By.xpath("//a[text()='Downloads']");
    private static final By EXCEL_OPTION = By.xpath("//a[text()='Excel']");


    public jQueryUIMenus(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);

    }

    public jQueryUIMenus navigateToPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public jQueryUIMenus hoverAndClickEnabled() {
        WebElement enabled = wait.until(ExpectedConditions.elementToBeClickable(ENABLED_MENU));
        actions.moveToElement(enabled).click().perform();
        return this;
    }

    public jQueryUIMenus hoverAndClickDownloads() {
        WebElement downloads = wait.until(ExpectedConditions.elementToBeClickable(DOWNLOADS_OPTION));
        actions.moveToElement(downloads).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        actions.moveToElement(downloads).click().perform();
        return this;
    }

    public void clickExcel() {
        WebElement excel = wait.until(ExpectedConditions.elementToBeClickable(EXCEL_OPTION));
        actions.moveToElement(excel).click().perform();
    }
    public boolean isFileDownloaded(String fileName) throws InterruptedException {
        waitForSeconds(8); // Wait for the download to complete
        File downloadDir = new File(downloadFolder);
        File[] files = downloadDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }
}

