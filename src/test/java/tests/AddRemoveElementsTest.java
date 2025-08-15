package tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddRemoveElements;

public class AddRemoveElementsTest extends BaseDriver {
    private static final int ELEMENTS_TO_ADD = 20;
    private static final int FINAL_COUNT = 0;

    @Test(priority = 1, description = "Add 20 elements sequentially with validation after each addition")
    public void testAdd20ElementsSequentially() {
        AddRemoveElements addRemovePage = new AddRemoveElements(driver,wait);
        addRemovePage.openPage();
        System.out.println("=== Starting Test: Add 20 Elements Sequentially ===");

        // Verify initial state - no elements should be present
        //  Assert.assertEquals(addRemovePage.getDeleteButtonCount(), 0, "Initial element count should be 0");

        addRemovePage.addMultipleElementsAndValidate(ELEMENTS_TO_ADD);// Add 20 elements sequentially

        //  validation - should have exactly 20 elements
        int firstCount = addRemovePage.getDeleteButtonCount();
        Assert.assertEquals(firstCount, ELEMENTS_TO_ADD, "Final element count validation failed. Expected: " + ELEMENTS_TO_ADD + ", Actual: " + firstCount);
        // Final validation - should have exactly 0 elements
        addRemovePage.deleteEachElementsAndValidate();
        int finalCount = addRemovePage.getDeleteButtonCount();
        Assert.assertEquals(finalCount, FINAL_COUNT, "Final element count validation failed. Expected: " + FINAL_COUNT + ", Actual: " + finalCount);
    }
}
