package com.qa.VR4.base;
import java.util.Properties;

import com.qa.VR4.pages.*;
import com.qa.VR4.utils.JavaScriptUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.qa.VR4.factory.DriverFactory;
import com.qa.VR4.utils.SessionManager;
import com.qa.VR4.utils.VRUtils;
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

    protected JavaScriptUtil js;
    @BeforeTest
    public void setup() {
        df=new DriverFactory();
        prop=df.initprop();
        driver= df.initDriver(prop);
        loginPage=new LoginPage(driver);
        disPage =new DispatchPage(driver);
        resegrid=new ReservationToInspectionPage(driver);
        housePage=new housekeepingWithReservationfunPage(driver);
        ownerloginPage =new OwnerLoginPage(driver);
        wlogin=new withoutloginPage(driver);
        vrutil=new VRUtils(driver);
        js = new JavaScriptUtil(driver);
    }
    @AfterTest
    public void tearDown() {
        //driver.quit();
    }

}
