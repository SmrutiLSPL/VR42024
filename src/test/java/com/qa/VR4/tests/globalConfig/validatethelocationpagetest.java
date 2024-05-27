package com.qa.VR4.tests.globalConfig;

import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class validatethelocationpagetest extends BaseTest {

    @BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    @Test(priority = 1)
    public void openlocationMenu() throws InterruptedException {
        vrutil.waitforMenuElementVisible(10,"Global Configuration");
        vrutil.waitforMenuElementVisible(20,"General");
        glConfig.scrollSunMenu(10,"Locations");
    }
    @Test(priority = 2)
    public void validateerrorMsgWithouEnterAnyValue() throws InterruptedException {
        String actwarningmsg=location.warningMsgValidate(10);
        Assert.assertEquals(actwarningmsg,"Please Enter Location Name","No Warning message");
    }
    @Test(priority =3)
    public void validatthelocationformwithvalidata() throws InterruptedException {
        String expLocation=  location.enterLocationWithValidData(10,"SmrutiTest1","test SMR area name1");
        location.selectRestro(10,"SeaCrest Beach");
       String actLocation= location.validateLocationName(10);
       Assert.assertEquals(actLocation,expLocation,"Location Name not found");

    }
    @Test(priority =4)
    public void validateDeleteLocation() throws InterruptedException {
        location.deleteLocationName(10,"SmrutiTest1");
    }

    @Test(priority =4)
    public void validateEdiLocation() throws InterruptedException {
        location.editLocationName(10,"NewLocationName","LocationName");

    }




}
