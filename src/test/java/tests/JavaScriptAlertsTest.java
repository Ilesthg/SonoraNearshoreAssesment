package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.JavaScriptAlerts;

public class JavaScriptAlertsTest extends BaseDriver {
    @Test
    public void testJavaScriptAlerts() throws InterruptedException {
        JavaScriptAlerts   javaScriptAlerts = new JavaScriptAlerts(driver);
        javaScriptAlerts.goToPage();
        javaScriptAlerts.clickJSAlertButton();
        javaScriptAlerts.acceptAlert();

        Assert.assertTrue(javaScriptAlerts.confirmationAlertMessage());

        javaScriptAlerts.clickJSPromptButton();
        String promptText = "testing";
        javaScriptAlerts.fillPromptAndAccept(promptText);
        Assert.assertTrue(javaScriptAlerts.assertResultText(promptText));

    }
}
