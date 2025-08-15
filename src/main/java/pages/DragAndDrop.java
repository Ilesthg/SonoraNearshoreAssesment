package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CustomMethods;

public class DragAndDrop {
    private static final String URL = "https://the-internet.herokuapp.com/drag_and_drop";
    private static final String TITLE = "Drag and Drop";
    private static final By COLUMN_A = By.xpath("//div[@id='column-a']");
    private static final By COLUMN_B = By.xpath("//div[@id='column-b']");
    private static String initialA;
    private static String initialB;
    private WebDriver driver;
    private WebDriverWait wait;
    private CustomMethods customMethods;

    public DragAndDrop(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.customMethods = new CustomMethods(this.driver);
    }

    public DragAndDrop openPage() {
       customMethods.openPageAndValidateTitle(URL, TITLE);
        initialA = wait.until(ExpectedConditions.visibilityOfElementLocated(COLUMN_A)).getText(); //a
        initialB = wait.until(ExpectedConditions.visibilityOfElementLocated(COLUMN_B)).getText(); //b
        return this;
    }


    public void dragAndDrop() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(COLUMN_A))
                .moveToElement(driver.findElement(COLUMN_B))
                .release()
                .build()
                .perform();
    }


    public boolean elementsSwitched() {
        // After dragging and dropping, check if the elements have switched places
//After switching, the text in COLUMN_A should be "B" and in COLUMN_B should be "A"
        String currentA = driver.findElement(COLUMN_A).getText(); //B
        String currentB = driver.findElement(COLUMN_B).getText();//A

        if (currentA.equals(initialB) && currentB.equals(initialA)) {
            System.out.println("Elements have been switched successfully." +
                               " Column A: " + currentA + ", Column B: " + currentB);
            initialA = currentA; // Update initial values after switch
            initialB = currentB; // Update initial values after switch
            return true;
        } else {
            System.out.println("Elements have not been switched. Column A: " + currentA + ", Column B: " + currentB);
            return false;
        }

    }
}
