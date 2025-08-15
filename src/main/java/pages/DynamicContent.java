package pages;

import base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CustomMethods;

import java.util.HashSet;
import java.util.List;

public class DynamicContent {
    private static final String URL = "https://the-internet.herokuapp.com/dynamic_content";
    private static final String EXPECTED_PAGE_TITLE = "Dynamic Content";
    private static final By DYNAMIC_CONTENT_HEADER = By.xpath("//h3[normalize-space()='Dynamic Content']");
    private static final By DYNAMIC_CONTENT_IMAGE = By.xpath("//img[starts-with(@src,'/img/avatars/Original')]");//List of elements
    private static final int EXPECTED_TOTAL_IMAGES = 5;
    private CustomMethods customMethods;

    private WebDriver driver;

    private static HashSet<String> imageSet = new HashSet<>(); // To store unique image URLs

    public DynamicContent(WebDriver driver) {
        this.driver = driver;
        this.customMethods = new CustomMethods(this.driver);
    }
    public DynamicContent openPage() {
        customMethods.openPageAndValidateTitle(URL, EXPECTED_PAGE_TITLE);
        return this;
    }

    public void getAllImagesAtLeastOnce() {
        // Refresh page many times to find all different images

        for (int i = 0; i < 20; i++) {
            System.out.println("Refresh number: " + i);

            // Get all images on page
            List<WebElement> images = driver.findElements(DYNAMIC_CONTENT_IMAGE);

            // Check each image
            for (WebElement image : images) {
                String imageUrl = image.getAttribute("src");

                if (imageUrl != null) {
                    System.out.println("Found image: " + imageUrl);

                    // Add to list if not already there
                    if (!imageSet.contains(imageUrl)) {
                        imageSet.add(imageUrl);
                    }
                }
            }
            // Break if we found 5 images
            if (imageSet.size() >= EXPECTED_TOTAL_IMAGES) {
                System.out.println("Found all 5 images");
                break;
            }
            // Refresh page
            driver.navigate().refresh();
        }

        System.out.println("Final count: " + imageSet.size() + " different images");
    }
    public int getTotalImages() {
        // This method retrieves the total number of unique images found on the page.
        return imageSet.size();
    }

    public String getActualURL() {
        // This method retrieves the title of the current page.
        String title = driver.getCurrentUrl();
        return title;
    }

    public String getHeaderText() {
        // This method retrieves the header text of the dynamic content page.
        String headerText = driver.findElement(DYNAMIC_CONTENT_HEADER).getText();
        return headerText;
    }

}
