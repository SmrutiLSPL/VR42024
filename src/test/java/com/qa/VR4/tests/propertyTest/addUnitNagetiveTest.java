package com.qa.VR4.tests.propertyTest;

import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addUnitNagetiveTest extends BaseTest {

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
    public void validateWarningMsg() throws InterruptedException {

        addProperty.selecttab("Summary", 15);

        String actMsg=addProperty.addUnitWithNextButton();
        Assert.assertEquals(actMsg,"Please Fill All Required Details");

    }
    @Test(priority = 3)
    public void validateWarningNumberWithSummmaryTab() throws InterruptedException {

        int actValue= addProperty.addUnitFormWithSaveButton( "001","");
        System.out.println(actValue);
        Assert.assertEquals(actValue,3);

    }
    @Test(priority = 4)
    public void validateWarningNumberWithlocationTab() throws InterruptedException {

        addProperty.selecttab("Location", 5);

        int actValue=  addProperty.addUnitFormWithSaveButton(10,"BBG- Best Beach Getaways");
        Assert.assertEquals(actValue,3);
    }



}
