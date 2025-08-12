package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckBoxes;
import pages.DragAndDrop;

public class CheckBoxesTest extends BaseDriver {
    @Test
    public void checkboxesTest() {
        CheckBoxes checkBoxes = new CheckBoxes(driver,wait);
        checkBoxes.navigateTo().getAllCheckboxesInMap();

    }
}
