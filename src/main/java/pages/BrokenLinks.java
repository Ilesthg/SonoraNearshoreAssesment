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
    private static final String TITLE = "Broken Images";
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
       customMethods.openPageAndValidateTitle(BASE_URL, TITLE);
        return this;
    }

    boolean isImageOk(WebElement img) {
        String complete = img.getDomProperty("complete");       // "true"/"false"
        String nw = img.getDomProperty("naturalWidth");         // "0" for broken, >0 for loaded
        return Boolean.parseBoolean(complete) && Integer.parseInt(nw) > 0;
    }


    public void counterBrokenImages() {
        List<WebElement> images = driver.findElements(IMAGE_LIST);
        for (WebElement image : images) {
            String imageUrl = image.getAttribute("src");

            if (isImageOk(image)) {
                workingCount++;
                System.out.println("Broken Image Found: " + imageUrl);
            } else {
                brokenCount++;
                System.out.println("Working Image Found: " + imageUrl);
            }
        }
    }
    public int getBrokenCount() {
        return brokenCount;
    }

    public int getWorkingCount() {
        return workingCount;
    }

}
