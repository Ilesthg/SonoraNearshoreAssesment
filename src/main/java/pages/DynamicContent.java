package pages;

import base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;

public class DynamicContent {
    /* public static final String DYNAMIC_CONTENT_IMAGE = "https://the-internet.herokuapp.com/img/avatars/Original-Avatar.png";
     public static final String DYNAMIC_CONTENT_BUTTON = "To make some of the content static append ?with_content=static";
     public static final String DYNAMIC_CONTENT_URL = "https://the-internet.herokuapp.com/dynamic_content";*/
    public static final By DYNAMIC_CONTENT_HEADER = By.xpath("//h3[normalize-space()='Dynamic Content']");
    //public static final By DYNAMIC_CONTENT_TEXT = By.xpath("//p[@xpath = '1']");
    public static final By DYNAMIC_CONTENT_IMAGE = By.xpath("//img[starts-with(@src,'/img/avatars/Original')]");//List of elements
    private static final int STABLE_ROUNDS = 8;

    private WebDriver driver;

    private static HashSet<String> imageSet = new HashSet<>(); // To store unique image URLs

    public DynamicContent(WebDriver driver) {
        this.driver = driver;
    }

    public void getAllImagesAtLeastOnce() {
        // This method is to ensure that all images are loaded at least once. Images are rendered on server side
       //Elementos del primer DOM, AFTER the page is refresh this get lost

        int discovered = 0;
        for (int i = 0; i < STABLE_ROUNDS; i++) {
            List<WebElement> images = driver.findElements(DYNAMIC_CONTENT_IMAGE);
            int before = imageSet.size();//0
            for (WebElement image : images) {
                String imageUrl = image.getAttribute("src");


                if (imageUrl != null && !imageUrl.isEmpty()) {
                    System.out.println("imageUrl = " + imageUrl);
                    if (!imageSet.contains(imageUrl)) {
                        imageSet.add(imageUrl);
                    }
                }

            }
            discovered = imageSet.size() - before;
            driver.navigate().refresh();

        }
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
 /*   public String getContentText(){
        // This method retrieves the header text of the dynamic content page.
        String contentText = driver.findElement(DYNAMIC_CONTENT_TEXT).getText();
        return contentText;
    }*/
}
