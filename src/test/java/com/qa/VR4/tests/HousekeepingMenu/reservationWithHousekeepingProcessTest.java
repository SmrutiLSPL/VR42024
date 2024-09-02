package com.qa.VR4.tests.HousekeepingMenu;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class reservationWithHousekeepingProcessTest extends BaseTest {
    @BeforeTest
    public void login() {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    @Test(priority = 1)
    public void HousekeepingtaskwithReservationGridPageTest() throws InterruptedException {
        resegrid.clickmainmenu();
        resegrid.clickOnListOfMenu();
        vrutil.selectReservationGrid("SHORE146", 3, "19");

    }

//    @Test(priority = 2)
//    public void Housekeepingtaskwith() throws InterruptedException {
//        resegrid.clickmainmenu();
//        resegrid.clickOnListOfMenu();
//        vrutil.selectReservationGrid("SHORE146", 3, "16");
//    }
//
//    @Test(priority = 2)
//    public void validatetheHousekeepingtaskwithCheckoutDateTest() throws InterruptedException {
//        disPage.clickOnInspectionMenu();
//        disPage.clickOnDispatchListOfMenu();
//        disPage.openDatePicker();
//        vrutil.selectDate("16", "JULY", "2024");
//
//        disPage.openInspectionUnit("SHORE146");
//    }


}
