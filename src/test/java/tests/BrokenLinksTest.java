package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrokenLinks;

public class BrokenLinksTest extends BaseDriver {
    @Test(priority = 1, description = "Open the Broken Links page and count broken links")
    public void testBrokenLinks() {
        BrokenLinks brokenLinksPage = new BrokenLinks(driver,wait);
        brokenLinksPage.navigateToPage();
        brokenLinksPage.counterBrokenImages();
        brokenLinksPage.getBrokenCount();
        brokenLinksPage.getWorkingCount();

        Assert.assertEquals(brokenLinksPage.getBrokenCount(), 2, "Broken links count does not match expected value.");
        Assert.assertEquals(brokenLinksPage.getWorkingCount(), 1, "Working links count does not match expected value.");
    }
}
