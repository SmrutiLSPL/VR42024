package com.qa.VR4.tests;

import com.qa.VR4.base.BaseTest;

import com.qa.VR4.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TaskTest extends BaseTest {
	
	@BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }
	
    @Test(priority = 1)
    public void ReservationGridPageTest() throws InterruptedException {
        resegrid.clickmainmenu();
        resegrid.clickOnListOfMenu();
        vrutil.selectReservationGrid("AQ111",3, "30");
    }
//    @Test(priority = 2)
//    public void InpectionPageClickTest() throws InterruptedException {
//
//        disPage.clickOnInspectionMenu("Dispatch", AppConstants.SHORT_TIME_OUT);
//        disPage.openDatePicker();
//        vrutil.selectDate("30","MARCH","2024");
//        disPage.openInspectionUnitMarkCompleteTask("AQ111","Mark Completed");
//    }







}
