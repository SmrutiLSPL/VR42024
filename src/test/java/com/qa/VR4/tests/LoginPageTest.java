package com.qa.VR4.tests;

import com.qa.VR4.base.BaseTest;
import com.qa.VR4.constants.AppConstants;
import org.apache.log4j.Priority;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @DataProvider
    public Object[][] loginTestData() {
        return new Object[][]{
                {"bestbeach1", "126456", "Select view or Default"},
                {"bestbeach", "789765465", "Select view or Default"},
                {"bestbeach1", "Winter-house2021", "Select view or Default"},
        };
    }
    @Test(dataProvider = "loginTestData", priority = 1)
    public void loginWithWrongDataTest(String username, String pwd, String visibleText) throws InterruptedException {
        String acttitle = loginPage.VRloginForNegative(username, pwd, visibleText);
        System.out.println(acttitle);
        Assert.assertEquals(acttitle, AppConstants.LOGIN_PAGE_VALIDATION_ERROR);
    }
    @Test(priority = 2)
    public void LoginwithoutcredentialTest() throws InterruptedException {

        String accPage = loginPage.VRloginForNegative("", "", "Select view or Default");
        Assert.assertEquals(accPage, AppConstants.LOGIN_PAGE1_VALIDATION_ERROR);
    }
    @Test(priority = 3)
    public void loginValidDataTest() throws InterruptedException {
        loginPage.VRlogin(AppConstants.USERNAME, AppConstants.PWD, AppConstants.OPS_VIEW);
        ;
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.OPS_TITLE);
    }
    @Test(priority = 4)
    public void LoginwithFieldManagercredentialTest() throws InterruptedException {

        loginPage.VRlogin(AppConstants.USERNAME, AppConstants.PWD, AppConstants.INSPECTOR_VIEW);
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.INSPECTOR_TITLE);
    }
    @Test(priority = 5)
    public void LoginwithhouskeepingcredentialTest() throws InterruptedException {

        loginPage.VRlogin(AppConstants.USERNAME, AppConstants.PWD, AppConstants.HOUSEKEEPING_VIEW);
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.HOUSEKEEPING_TITLE);
    }
    @Test(priority = 6)
    public void LoginwithServicecredentialTest() throws InterruptedException {

        loginPage.VRlogin(AppConstants.USERNAME, AppConstants.PWD, AppConstants.SERVICE_VIEW);
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.SERVICE_TITLE);
    }
    @AfterMethod
    public void deletecookies() {
        driver.manage().deleteAllCookies();
        driver.get("https://vrmanaged1.com/");
    }

}
