package com.qa.VR4.tests.HousekeepingMenu;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HousekeepingTest extends BaseTest {
    @BeforeTest
    public void login() {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    @Test(priority = 1)
    public void openHousekeeping() throws InterruptedException {
        disPage.openDatePicker();
        vrutil.selectDate("19", "JULY", "2024");
        disPage.openHousekeepingUnit("SHORE146");
    }
    @Test(priority = 2)
    public void openHousekeepingandComplateLPTask() throws InterruptedException {
        disPage.openLaundryTask("SHORE146","LP");
    }
    @Test(priority = 2)
    public void openHousekeepingandComplateLDTask() throws InterruptedException {
        disPage.openLaundryTask("SHORE146","LD");
    }

//    @Test(priority = 2)
//    public void validatetheHousekeepingtaskwithCheckoutDateTest() throws InterruptedException {
//        disPage.clickOnInspectionMenu();
//        disPage.clickOnDispatchListOfMenu();
//        disPage.openDatePicker();
//        vrutil.selectDate("16", "JULY", "2024");
//
//        disPage.openInspectionUnit("SHORE146");
//    }
//    @Test(priority = 3)
//    public void validatetheHousekeepingtaskwithmarkcompleteActnTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("16", "JULY", "2024");
//        vrutil.ValidateUnitWithActionButton("SHORE146","Mark Completed");
//    }
//    @Test(priority = 4)
//    public void openHousekeepingUnitValidatewithRecleanbtnTaskTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("16", "JULY", "2024");
//      //  housePage.openHousekeepingUnitValidatewithRecleanbtnTask("36RUE","Re-Clean");
//        vrutil.ValidateScrollUnitWithActionButton("SHORE146","Re-Clean");
//    }
//    @Test(priority = 5)
//    public void housekeepingcurrentPageUnitvalidatewithCheckIntaskTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("16", "JULY", "2024");
//        vrutil.ValidateUnitWithActionButton("SHORE146","Check In");
//    }
//    @Test(priority = 6)
//    public void housekeepingcurrentPageUnitvalidatewithCheckouttaskTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("16", "JULY", "2024");
//
//        vrutil.ValidateUnitWithActionButton("SHORE146","Check Out");
//    }
//
//    @Test(priority = 7)
//    public void housekeepingfuturePageUnitvalidatewithCheckIntaskTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("23", "MARCH", "2024");
//       // housePage.openHousekeepingUnitValidatewithcheckInfuturedateTask("RAB210","Check In");
//        vrutil.ValidateScrollUnitWithActionButton("SHORE146","Check In");
//    }
//    @Test(priority = 8)
//    public void housekeepingUnitvalidatewithResetTaskTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("21", "MARCH", "2024");
//        vrutil.ValidateUnitWithActionButton("SHORE146","Reset");
//    }
//    @Test(priority = 9)
//    public void housekeepingUnitvalidatewithOwnerCleanTaskTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("21", "MARCH", "2024");
//        vrutil.ValidateUnitWithActionButton("SHORE146","Owner Clean");
//    }
//
//    @Test(priority = 10)
//    public void housekeepingvalidatewithNewHousekeeperUserTest() throws InterruptedException {
//        disPage.openDatePicker();
//        vrutil.selectDate("21", "MARCH", "2024");
//        vrutil.selectUnitforDispatchPage("SHORE146");
//        housePage.selectNewHousekeeperUser("SmrutiQA");
//    }
//    @Test(priority = 11)
//    public void housekeepingvalidateTest() throws InterruptedException {
//
//
//        housePage.validateCurrentdate("WSC-A316","22", "MARCH", "2024");
//    }

}

