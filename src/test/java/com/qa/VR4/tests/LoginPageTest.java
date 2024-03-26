package com.qa.VR4.tests;
import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
public class LoginPageTest extends BaseTest {


    @Test
    public void LoginPageTitleTest()
    {
        String loginTitle= loginPage.getLoginPageTitle();
        System.out.println(loginTitle);
    }

    @Test
    public void LoginvalidcredentialsTest() throws InterruptedException {

        String accPage = loginPage.VRLogin(prop.getProperty("username"), prop.getProperty("password"),prop.getProperty("selectView"));
        Assert.assertEquals(accPage,"VR WORKS - Dispatch");
    }
    @Test
    public void LoginAllInvalidcredentialsTest() throws InterruptedException {

        String accPage = loginPage.VRloginWithAllInvaliddata("bestbeach1","126456","Select view or Default");
      Assert.assertEquals(accPage,"Incorrect Email/Username or Password");
    }

    @Test
    public void LoginEmailInvalidcredentialTest() throws InterruptedException {

        String accPage = loginPage.VRloginWithAllInvaliddata("bestbeach1","Winter-house2021","Select view or Default");
        Assert.assertEquals(accPage,"Incorrect Email/Username or Password");
    }
    @Test
    public void LoginpasswordInvalidcredentialTest() throws InterruptedException {

        String accPage = loginPage.VRloginWithAllInvaliddata("bestbeach","Winter-house2000","Select view or Default");
        Assert.assertEquals(accPage,"Incorrect Email/Username or Password");
    }
    @Test
    public void LoginwithoutcredentialTest() throws InterruptedException {

        String accPage = loginPage.VRloginWithAllInvaliddata("","","Select view or Default");
        Assert.assertEquals(accPage,"Please enter email or username Please enter password");
    }
    @Test
    public void LoginwithFieldManagercredentialTest() throws InterruptedException {

        String accPage = loginPage.VRLogin("bestbeach","Winter-house2021","Field Manager");
        Assert.assertEquals(accPage,"VR INSPECTORInspector");
    }

    @Test
    public void LoginwithhouskeepingcredentialTest() throws InterruptedException {

        String accPage = loginPage.VRLogin("bestbeach","Winter-house2021","Cleaning");
        Assert.assertEquals(accPage,"VR HOUSEHousekeeperunits");
    }

    @Test
    public void LoginwithServicecredentialTest() throws InterruptedException {

        String accPage = loginPage.VRLogin("bestbeach","Winter-house2021","Service");
        Assert.assertEquals(accPage,"Newservice");
    }




}
