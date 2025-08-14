package pages;


import org.bouncycastle.jcajce.provider.drbg.DRBG;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CustomMethods;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks {
    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com/broken_images";
    private WebDriverWait wait;
    private CustomMethods customMethods;
    private int brokenCount = 0;
    private  int workingCount = 0;

    private static final By IMAGE_LIST = By.xpath("//div[@class='example']/img");

    public BrokenLinks(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.customMethods = new CustomMethods(driver);
    }

    public BrokenLinks navigateToPage() {
        driver.get(BASE_URL);
        return this;
    }

    boolean isImageOk(WebElement img) {
        String complete = img.getDomProperty("complete");       // "true"/"false"
        String nw = img.getDomProperty("naturalWidth");         // "0" for broken, >0 for loaded
        return Boolean.parseBoolean(complete) && Integer.parseInt(nw) > 0;
    }

    /**
     * Check if image is broken using naturalWidth property
     * Broken images have naturalWidth = 0
     */

    private boolean isImageBrokenByNaturalWidth(WebElement image) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object naturalWidth = js.executeScript("return arguments[0].naturalWidth;", image);
        return naturalWidth.equals(0L);

    }

    /**
     * Check if image is broken by sending HTTP request to image URL
     */
    private boolean isImageBrokenByHttpStatus(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            connection.disconnect();

            // Consider image broken if status code is not 200
            return responseCode != 200;
        } catch (IOException e) {
            System.err.println("Error checking image URL: " + imageUrl + " - " + e.getMessage());
            return true; // Consider broken if we can't connect
        }
    }

    /**
     * Check if image is wrapped in an anchor tag
     */
    private boolean isImageHasLink(WebElement image) {
        try {
            WebElement parentElement = image.findElement(By.xpath(".."));
            return "a".equals(parentElement.getTagName().toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
    public void counterBrokenImages() {
        List<WebElement> images = driver.findElements(IMAGE_LIST);
        for (WebElement image : images) {
            String imageUrl = image.getAttribute("src");

            if (isImageOk(image)) {
                brokenCount++;
                System.out.println("Broken Image Found: " + imageUrl);
            } else {
                workingCount++;
                System.out.println("Working Image Found: " + imageUrl);
            }
        }
    }
    public int getBrokenCount() {
        System.out.println("Total Broken Images: " + brokenCount);
        return brokenCount;
    }

    public int getWorkingCount() {
        System.out.println("Total Working Images: " + workingCount);
        return workingCount;
    }

}
