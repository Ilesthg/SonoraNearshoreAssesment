package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.jQueryUIMenus;

public class jQueryUIMenusTest extends BaseDriver {
    @Test
    public void testExcelFileDownload() throws InterruptedException {
        jQueryUIMenus menus = new jQueryUIMenus(driver);
        menus.navigateToPage()
                .hoverAndClickEnabled()
                .hoverAndClickDownloads()
                .clickExcel();

        // Verify the file is downloaded
        boolean isDownloaded = menus.isFileDownloaded("menu.xls");
        Assert.assertTrue(isDownloaded, "Excel file was not downloaded successfully.");
    }
}
