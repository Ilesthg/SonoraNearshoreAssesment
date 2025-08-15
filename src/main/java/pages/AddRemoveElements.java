package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CustomMethods;

import java.util.List;

public class AddRemoveElements {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL = "https://the-internet.herokuapp.com/add_remove_elements/";
    private static final By ADD_ELEMENT_BUTTON = By.xpath("//button[@onclick='addElement()']");
    private static final By DELETE_BUTTONS = By.xpath("//button[@class='added-manually']");
    private static final String EXPECTED_PAGE_TITLE = "Add/Remove Elements";

    private String dynamicButtons = "//*[@id='elements']/button[$value]"; // Dynamic buttons locator
    private CustomMethods customMethods;

    public AddRemoveElements(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.customMethods = new CustomMethods(this.driver);
    }

    public AddRemoveElements openPage() {
        customMethods.openPageAndValidateTitle(URL, EXPECTED_PAGE_TITLE);
        return this;
    }


    // Add a single element
    public void addElement() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_ELEMENT_BUTTON)).click();
    }

    public void addMultipleElementsAndValidate(int count) {
        int counter = 0; //
        while (counter < count) {
            addElement();
            int currentCount = getDeleteButtonCount();//0

            if (currentCount == counter + 1) {
                System.out.println("Element added successfully. Current count: " + currentCount);
                counter++;
            } else {
                System.out.println("Expected count: " + (counter + 1) + ", but got: " + currentCount);
                break;
            }
        }
    }

    public void deleteEachElementsAndValidate() {
        int totalElements = getDeleteButtonCount(); //total of elements
        for (int i = 0; i < totalElements; i++) {
            By firstDeleteButton = By.xpath(getDynamicButtonXPath(1)); // Get the first delete button dynamically
            wait.until(ExpectedConditions.elementToBeClickable(firstDeleteButton)).click();
            int currentCount = getDeleteButtonCount();
            System.out.println("Element deleted successfully. Current count: " + currentCount);
        }
    }


    //Dynamic method
    public String getDynamicButtonXPath(int value) {
        return dynamicButtons.replace("$value", String.valueOf(value));
    }

    // Validate all elements are added
    public boolean validateDeleteButtonsAdded(int count) { //20 == 20 true
        return count == getDeleteButtonCount();
    }


    // Get count of delete buttons (elements)
    public int getDeleteButtonCount() {
        try {
            List<WebElement> listOfDeleteButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DELETE_BUTTONS));
            return listOfDeleteButtons.size();
        } catch (Exception e) {
            return 0; // Return 0 if no elements found
        }
    }


}
