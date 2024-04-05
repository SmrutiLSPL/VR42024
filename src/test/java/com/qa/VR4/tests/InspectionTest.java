package com.qa.VR4.tests;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InspectionTest extends BaseTest {
    @BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

   @Test
    public void OpenDispatchMenuWithMarkCompleteTaskTest() throws InterruptedException {
       // disPage.clickOnInspectionMenu();
       //disPage.clickOnDispatchListOfMenu();
       disPage.openDatePicker("30", "MARCH", "2024");

       disPage.openInspectionUnitMarkCompleteTask("AQ111","Mark Completed");
   }
    @Test
    public void OpenDispatchMenuWithResetTest() throws InterruptedException {
        // disPage.clickOnInspectionMenu();
        //disPage.clickOnDispatchListOfMenu();
        disPage.openDatePicker("30", "MARCH", "2024");

        disPage.openInspectionUnitResetTask("AQ111","Reset", "30");
    }

    @Test
    public void OpenDispatchMenuWithCheckInTest() throws InterruptedException {
        disPage.openDatePicker("30", "MARCH", "2024");

        disPage.openInspectionUnitCheckInTask("AQ111","Check In","30");
    }
    @Test
    public void openInspectionUnitCheckOutTaskTest() throws InterruptedException {

        disPage.openInspectionUnitCheckOutTask("AQ111","Check Out");
    }
    @Test
    public void openInspectionUnitCheckOutTaskWithResetTest() throws InterruptedException {

        disPage.MarkCompleteWithResetFunction("30","AQ111","Reset");
    }

    @Test
    public void openInspectionUnitwithHoldTaskTest() throws InterruptedException {
        disPage.openDatePicker("30", "MARCH", "2024");

        disPage.openInspectionUnitHoldTask("AQ111","Hold");
    }
    @Test
    public void openInspectionUnitwithReleaseTaskTest() throws InterruptedException {
        disPage.openDatePicker("30", "MARCH", "2024");

        disPage.openInspectionUnitHoldWithReleaseTask("AQ111","Release");
    }
    @Test
    public void openInspectionUnitwithResetHoldReleaseTaskTest() throws InterruptedException {
        disPage.openDatePicker("19", "MARCH", "2024");

        disPage.openInspectionUnitHoldWithResetHoldReleaseTask("36RUE","Reset Hold/Release");
    }

    @Test
    public void selectnewUserForInspectionTaskTest() throws InterruptedException {
        disPage.openDatePicker("20", "MARCH", "2024");


        vrutil.selectUnitforDispatchPage("AMAZING");
        disPage.selectNewInspectionUser("SmrutiQA");

    }
    @Test
    public void selectNewDateForInspectionTaskAndValidateWithNewDateTest() throws InterruptedException {
        disPage.openDatePicker("20", "MARCH", "2024");

        vrutil.selectUnitforDispatchPage("AMAZING");
        disPage.selectNewDateForInspectionTaskAndValidateWithNewDate();

    }


}
