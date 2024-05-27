package com.qa.VR4.tests.propertyTest;

import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddNewUnitPositiveTest extends BaseTest {

    @BeforeTest
    public void login() {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    @Test(priority = 1)
    public void openAddUnit() throws InterruptedException {
        addProperty.clickmainmenu();
        vrutil.clickMaintenanceMenuWhenReady("Properties", 5);
        addProperty.openAddUnitOption();
    }

    @Test(priority = 2)
    public void validateAddUnitFormWithAllDatafields() throws InterruptedException {
        addProperty.addUnitsummaryFormWithSaveButton("202", "TestUnit", "TestUnit", "marketing demo", "test description", 10);
        addProperty.addUnitFormWithSaveButton(20, "BBG- Best Beach Getaways", "Panama City Beach", "Default", "address1",
                "adderess2", "estState", "city", "392001", "18.23", "5.0", "9878457854", "9878457485");
        String actValue = addProperty.addUnitWithDetailsTab(10, "Panama City Beach", "Blue Lupine", "House", "5");

        Assert.assertEquals(actValue, "Success! Unit Created.");
    }

    @Test(priority = 3)
    public void validateAddUnitWithSameUnitCode() throws InterruptedException {
        addProperty.addUnitsummaryFormWithSaveButton("102", "TestUnit", "TestUnit", "marketing demo", "test description", 10);
        addProperty.addUnitFormWithSaveButton(20, "BBG- Best Beach Getaways", "Panama City Beach", "Default", "address1",
                "adderess2", "estState", "city", "392001", "18.23", "5.0", "9878457854", "9878457485");
        String actValue = addProperty.addUnitWithDetailsTab(10, "Panama City Beach", "Blue Lupine", "House", "5");

        Assert.assertEquals(actValue, "UNIT CODE - This value is already in use");
    }


}
