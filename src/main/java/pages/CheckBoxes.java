package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CustomMethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckBoxes {

    //Default checkbox 2 is active
    //Need to checj UrL
    //NEED TO ASSER TITLE OFPAGE
    //Verify if checkvbox are check by default
    //Need to confirm with String on checkbox

    private static final String URL = "https://the-internet.herokuapp.com/checkboxes";
    private static final String EXPECTED_PAGE_TITLE = "Checkboxes";
  //  private static final By TITLE = By.xpath("//h3");
    private static final By CHECKBOXES = By.xpath("//form[@id='checkboxes']/input");
    private static final By CHECKBOXESTEXT = By.xpath("//*[@id='checkboxes']/text()");
    private static final Map<String, WebElement> hm = new HashMap<>();
    private WebDriver driver;
    private WebDriverWait wait;
    private CustomMethods customMethods;

    public CheckBoxes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.customMethods = new CustomMethods(driver);
    }

    public CheckBoxes navigateTo() {
       customMethods.openPageAndValidateTitle(URL, EXPECTED_PAGE_TITLE);
        return this;
    }


    public void getCheckBoxes() {
        hm.clear();
        List<WebElement> listCheckBoxes = driver.findElements(CHECKBOXES);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < listCheckBoxes.size(); i++) {
            WebElement checkbox = listCheckBoxes.get(i);

            // OPTION 1: Keep using the WebElement as context (RECOMMENDED)
            // This is already dynamic - it gets text for whichever checkbox is current
            String checkboxName = (String) js.executeScript(
                    "var result = document.evaluate('following-sibling::text()[1]', arguments[0], null, XPathResult.STRING_TYPE, null);" +
                            "return result.stringValue.trim().toLowerCase();;",
                    checkbox
            );
            hm.put(checkboxName, checkbox);

        }
    }

    public boolean isSelectCheckBox(String value) {
        return hm.get(value).isSelected();
    }

    public void checkBox(String value) {
        if (!hm.get(value).isSelected()) {
            hm.get(value).click();
        }
    }



}
