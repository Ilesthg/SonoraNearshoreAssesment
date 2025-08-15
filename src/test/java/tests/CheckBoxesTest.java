package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckBoxes;
import pages.DragAndDrop;

public class CheckBoxesTest extends BaseDriver {
    private static final String CHECKBOX1 = "checkbox 1";
    private static final String CHECKBOX2 = "checkbox 2";

    @Test(description = "Verify if the user is able to select and unselect the checkboxes")
    public void checkboxesTest() {
        CheckBoxes checkBoxes = new CheckBoxes(driver, wait);
        checkBoxes.navigateTo().getCheckBoxes();

        checkBoxes.isSelectCheckBox(CHECKBOX1);//Check if checkbox 1 is selected (should be FALSE)
        checkBoxes.checkBox(CHECKBOX1);//Check checkbox 1 --
        Assert.assertTrue(checkBoxes.isSelectCheckBox(CHECKBOX1), "Checkbox 1 is not check");//Assert that checkbox 1 is selected (should be TRUE)

        Assert.assertTrue(checkBoxes.isSelectCheckBox(CHECKBOX2), "Checkbox 2 is not check");






    }
}
