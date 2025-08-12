package tests;

import base.BaseDriver;
import org.testng.annotations.Test;
import pages.DynamicContent;

public class Herokuapp extends BaseDriver {

    @Test(priority = 1, description = "Open the dynamic content page")
    public void testOpeningPage(){
        driver.get("https://the-internet.herokuapp.com/dynamic_content");
        DynamicContent dynamicContent = new DynamicContent(driver);
        System.out.println("dynamicContent = " + dynamicContent.getActualURL());
        System.out.println("dynamicContent = " + dynamicContent.getHeaderText());
       dynamicContent.getAllImagesAtLeastOnce();
    }
   /* @Test(priority = 2, description = "Open Google")
    public void openGoogle() {
        driver.get("https://www.google.com/");

    }*/

}
