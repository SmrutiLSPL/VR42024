package com.qa.VR4.tests.HousekeepingMenu;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class arrivalDepartureTest extends BaseTest {

    @BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }
    @Test(priority = 1)
    public void OpenArrivalDeparturePageTest() throws InterruptedException, IOException {
        arrivalDepart.clickmainmenu();
        arrivalDepart.clickOnListOfMenu();
        arrivalDepart.clickOnsubListOfMenu("Arrival Departure");
    }
    @Test(priority = 2)
    public void validateArrivalDepartureDataTest() throws IOException, InterruptedException {
        arrivalDepart.openStartDatePicker();
        vrutil.selectDate("15", "MARCH", "2024");
        arrivalDepart.openEndDatePicker();
        vrutil.selectDate("15", "MARCH", "2024");
        arrivalDepart.clickgenerate();
        arrivalDepart.printADdata();
    }
    @Test(priority = 3)
    public void validateDispatchDataTest() throws IOException, InterruptedException {
        arrivalDepart.clickOnsubListOfMenu("Dispatch");
        arrivalDepart.opendispatchDatePicker();
        vrutil.selectDate("15", "MARCH", "2024");
        arrivalDepart.validateInspectionHousekeepingData();
    }
    @Test(priority = 4)
    public void validatedataWithDispatchdatatest() throws IOException {
        arrivalDepart.comparedatawithDispatch();
    }
    @Test(priority = 5)
    public void validatedatawithArrivalTest() throws IOException, InterruptedException {
        arrivalDepart.clickOnsubListOfMenu("Arrivals");
        arrivalDepart.openArrivalDatePicker();
        vrutil.selectDate("15", "MARCH", "2024");
        arrivalDepart.compareDataWithArrivalPage();
    }


}
