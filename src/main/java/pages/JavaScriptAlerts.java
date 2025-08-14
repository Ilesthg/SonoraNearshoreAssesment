package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptAlerts {
    private WebDriver driver;
    private WebDriverWait wait;
String url = "https://the-internet.herokuapp.com/javascript_alerts";
    private static final By JS_ALERT_BUTTON = By.xpath("//button[text()='Click for JS Alert']");
    private static final By JS_CONFIRM_BUTTON = By.xpath("//button[text()='Click for JS Confirm']");
    private static final By JS_PROMPT_BUTTON = By.xpath("//button[text()='Click for JS Prompt']");

    private static final By RESULT_TEXT = By.id("result");

    private static final By DESCRIPTIVE_TEXT = By.xpath("  //*[@id='content']/div/p[1]");
    public JavaScriptAlerts(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

public void goToPage() {
        driver.get(url);

    }
    public String getDescriptiveText() {
        WebElement descriptiveText = wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTIVE_TEXT));
        return descriptiveText.getText();

    }
    public void clickJSAlertButton() {
        driver.findElement(JS_ALERT_BUTTON).click();
    }

    public void clickJSPromptButton() {
        driver.findElement(JS_PROMPT_BUTTON).click();
    }

    private Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getResultText() {
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(RESULT_TEXT));
        return result.getText();
    }
    public boolean assertResultText(String expectedText) {
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(RESULT_TEXT));
        return result.getText().equals("You entered: "+expectedText);
    }

    public void acceptAlert() {
        Alert alert = waitForAlert();
        alert.accept();
    }

    public void fillPromptAndAccept(String text) {
        Alert prompt = waitForAlert();
        prompt.sendKeys(text);
        prompt.accept();
    }
    //You successfully clicked an alert
    public boolean confirmationAlertMessage() {
        String message = "You successfully clicked an alert";
        if (getResultText().equals(message)) {
            System.out.println("Alert accepted successfully and message is correct: " + message);
            return true;
        } else {
            System.out.println("Alert message is incorrect. Expected: " + message + ", but got: " + getResultText());
            return false;
        }

    }
}
