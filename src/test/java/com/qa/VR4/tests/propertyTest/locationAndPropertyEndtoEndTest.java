package com.qa.VR4.tests.propertyTest;

import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class locationAndPropertyEndtoEndTest extends BaseTest {


    @BeforeTest
    public void login() throws InterruptedException {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));

    }

    @Test(priority = 1)
    public void validatelocationNamewithlocationpageAndPropertyPage() throws InterruptedException {
        vrutil.waitforMenuElementVisible(30,"Global Configuration");
        vrutil.waitforMenuElementVisible(20,"General");
        glConfig.scrollSunMenu(10,"Locations");
        String actLocation=  location.enterLocationWithValidData(10,"NewLocationName","test SMR area name1");
        location.selectRestro(10,"SeaCrest Beach");
        addProperty.clickmainmenu();
        vrutil.clickMaintenanceMenuWhenReady("Properties", 5);
        addProperty.openUnitBySearchBox("205 Sand Oak Blvd",10);
        addProperty.selecttab("Location", 5);
        String expvalue = addProperty.validateLocationMenuWithNewLocationName(10,"NewLocationName");
        Assert.assertEquals(actLocation,expvalue,"do not match location");
    }
    @Test(priority = 2)
    public void validatetheEditLocationWithpropertypage() throws InterruptedException {
        glConfig.scrollSunMenu(10,"Global Configuration");
        vrutil.waitforMenuElementVisible(20,"General");
        glConfig.scrollSunMenu(10,"Locations");
        String actValue=location.editLocationName(10,"NewLocationName","LocationName");
        addProperty.clickmainmenu();
        vrutil.clickMaintenanceMenuWhenReady("Properties", 5);
        addProperty.openUnitBySearchBox("205 Sand Oak Blvd",10);
        addProperty.selecttab("Location", 5);
        String expvalue = addProperty.validateLocationMenuWithNewLocationName(10,"LocationName");
        Assert.assertEquals(actValue,expvalue,"do not match location");
    }

    @Test(priority = 2)
    public void validatethedeleteLocationWithpropertypage() throws InterruptedException {
        glConfig.scrollSunMenu(10,"Global Configuration");
        vrutil.waitforMenuElementVisible(20,"General");
        glConfig.scrollSunMenu(10,"Locations");
        location.deleteLocationName(10,"LocationName");
        addProperty.clickmainmenu();
        vrutil.clickMaintenanceMenuWhenReady("Properties", 5);
        addProperty.openUnitBySearchBox("205 Sand Oak Blvd",10);
        addProperty.selecttab("Location", 5);
        Boolean verifyTitle = addProperty.validateLocationMenuWithdeleteLocationName(10,"LocationName");
        assertFalse(verifyTitle);

    }



}
