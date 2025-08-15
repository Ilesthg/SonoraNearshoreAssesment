package tests;


import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DisappearingElements;

public class DisappearingElementsTest extends BaseDriver {


    @Test(description = "Verify if the Gallery button disappears and reappears upon refreshing the page")
    public void testDisappearingGalleryButton() {
        DisappearingElements disappearingElementsPage = new DisappearingElements(driver, wait);
        disappearingElementsPage.navigateToPage().refreshUntilElementExists();
        Assert.assertTrue(disappearingElementsPage.isElementPresent(), "Gallery button should be visible after refresh.");

        disappearingElementsPage.refreshUntilElementNotExists();
        Assert.assertFalse(disappearingElementsPage.isElementPresent(), "Gallery button should not be visible after refresh.");


    }
}
