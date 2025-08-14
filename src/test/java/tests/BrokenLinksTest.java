package tests;

import base.BaseDriver;
import org.testng.annotations.Test;
import pages.BrokenLinks;

public class BrokenLinksTest extends BaseDriver {
    @Test
    public void testBrokenLinks() {
        BrokenLinks brokenLinksPage = new BrokenLinks(driver,wait);
        brokenLinksPage.navigateToPage();
        brokenLinksPage.counterBrokenImages();
        brokenLinksPage.getBrokenCount();
        brokenLinksPage.getWorkingCount();
    }
}
