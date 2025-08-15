package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DynamicContent;

public class DynamicContentTest extends BaseDriver {

    @Test(priority = 1, description = "Open the dynamic content page")
    public void testOpeningPage() {

        DynamicContent dynamicContent = new DynamicContent(driver);
        dynamicContent.openPage();
        dynamicContent.getAllImagesAtLeastOnce();
        Assert.assertEquals(dynamicContent.getTotalImages(), 5, "The total number of images is not 5 as expected.");
    }


}
