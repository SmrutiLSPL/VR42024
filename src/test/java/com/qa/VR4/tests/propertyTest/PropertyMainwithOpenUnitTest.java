package com.qa.VR4.tests.propertyTest;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PropertyMainwithOpenUnitTest extends BaseTest {


    @BeforeTest


    public void login() {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));

    }

    @Test
    public void openPropertyTest() throws InterruptedException {
        addProperty.clickmainmenu();
        vrutil.clickMaintenanceMenuWhenReady("Properties", 5);
        addProperty.openUnitBySearchBox("205 Sand Oak Blvd",10);
        addProperty.selecttab("Location", 5);
    }


}
