package com.qa.VR4.base;
import java.util.Properties;

import com.qa.VR4.pages.*;
import com.qa.VR4.pages.GL.OfficesMenuPage;
import com.qa.VR4.pages.GL.globalConfigPage;
import com.qa.VR4.pages.GL.locationMenuPage;
import com.qa.VR4.pages.HousekeepingMenu.ArrivalDepartureReportPage;
import com.qa.VR4.pages.HousekeepingMenu.DispatchPage;
import com.qa.VR4.pages.HousekeepingMenu.ReservationToInspectionPage;
import com.qa.VR4.pages.HousekeepingMenu.housekeepingWithReservationfunPage;
import com.qa.VR4.pages.property.AddPropertyPage;
import com.qa.VR4.pages.property.EditPropertyPage;
import com.qa.VR4.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import com.qa.VR4.factory.DriverFactory;

public class BaseTest {
    public static WebDriver driver;

    protected Properties prop;

    protected withoutloginPage wlogin;
    DriverFactory df;
    public SessionManager sessionManager;
    public boolean isLoggedin = false;
    protected VRUtils vrutil;
    protected LoginPage loginPage;
    protected DispatchPage disPage;

    protected housekeepingWithReservationfunPage housePage;
    protected ReservationToInspectionPage resegrid;
    protected OwnerLoginPage ownerloginPage;
    protected ArrivalDepartureReportPage arrivalDepart;
    protected AddPropertyPage addProperty;
    protected EditPropertyPage editProperty;
    protected globalConfigPage glConfig;
    protected locationMenuPage location;
    protected OfficesMenuPage office;

    protected ExcelUtil xutils;
    protected String testCaseId;

    @BeforeTest
    public void setup() {
        df=new DriverFactory();
        prop=df.initprop();
        driver= df.initDriver(prop);
        loginPage=new LoginPage(driver);
        disPage =new DispatchPage(driver);
        addProperty =new AddPropertyPage(driver);
        resegrid=new ReservationToInspectionPage(driver);
        housePage=new housekeepingWithReservationfunPage(driver);
        ownerloginPage =new OwnerLoginPage(driver);
        editProperty =new EditPropertyPage(driver);
        arrivalDepart = new ArrivalDepartureReportPage(driver);
        glConfig=new globalConfigPage(driver);
        location=new locationMenuPage(driver);
        office=new OfficesMenuPage(driver);
        xutils =new ExcelUtil();
        wlogin=new withoutloginPage(driver);
        vrutil=new VRUtils(driver);

    }
    @BeforeTest
    public void tearDown() {
        //driver.quit();
    }
    @AfterMethod
    public void addResultsToTestRail(ITestResult result)
    {
        if(result.getStatus()==ITestResult.SUCCESS)
        {
            ClickUpTaskUpdater.updateTaskStatus(testCaseId,ClickUpTaskUpdater.TEST_CASE_PASS_STATUS,"");
        } else if (result.getStatus()==ITestResult.FAILURE) {
            ClickUpTaskUpdater.updateTaskStatus(testCaseId,ClickUpTaskUpdater.TEST_CASE_FAIL_STATUS,"Test got fail"
                    + result.getName()+": FAILED");
        }
    }


}
