package tests;

import base.BaseDriver;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragAndDrop;

public class DragAndDropTest extends BaseDriver {
    @Test
    public void dragAndDropTest() {
        DragAndDrop dragAndDrop = new DragAndDrop(driver, wait);
//Drag and drop the box A to the box B.
        dragAndDrop
                .openPage()
                .dragAndDrop();
        Assert.assertTrue(dragAndDrop.elementsSwitched(), "Drag and drop operation failed.");//Validate that the boxes were changed

        //Drag and drop the box B to the box A
        dragAndDrop.dragAndDrop();
        Assert.assertTrue(dragAndDrop.elementsSwitched(), "Drag and drop operation failed again.");//Validate that the boxes were changed
    }


}
