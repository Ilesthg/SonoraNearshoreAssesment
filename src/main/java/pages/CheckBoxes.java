package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckBoxes {

    //Default checkbox 2 is active
    //Need to checj UrL
    //NEED TO ASSER TITLE OFPAGE
    //Verify if checkvbox are check by default

    private static final String URL = "https://the-internet.herokuapp.com/checkboxes";
    private static final String PAGE_TITLE = "Checkboxes";
    private static final By TITLE = By.xpath("//h3");
    private static final By CHECKBOXES = By.xpath("//form[@id='checkboxes']/input");
    private static final By CHECKBOXESTEXT = By.xpath("//*[@id='checkboxes']/text()");
    private static final Map <Boolean, String>  hm = new HashMap<>();
    private WebDriver driver;
    private WebDriverWait wait;


    public CheckBoxes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public CheckBoxes navigateTo() {
        driver.get(URL);
        System.out.println(driver.findElement(TITLE).getText());
        if (!driver.findElement(TITLE).getText().equals(PAGE_TITLE)) {
            throw new IllegalStateException("This is not the CheckBoxes page");
        }
        return this;
    }
     List<WebElement> listCheckBoxes= driver.findElements(CHECKBOXES);
    static {


        for (  WebElement checkbox : listCheckBoxes) {
            String checkboxName = checkbox.getText();
            System.out.println("checkboxName = " + checkboxName);
            boolean checkboxValue = checkbox.isSelected();
            hm.put(checkboxValue, checkboxName);
        }
    }
    public void selectCheckBox(String checkboxName) {
            hm.get(checkboxName);
    }
   /* public void clickOnCheckBox(String checkboxName) {
        WebElement checkbox = driver.findElements(CHECKBOXES).get(index);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }*/


}
