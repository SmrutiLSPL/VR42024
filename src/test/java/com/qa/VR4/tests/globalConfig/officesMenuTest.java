package com.qa.VR4.tests.globalConfig;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;

public class officesMenuTest extends BaseTest {
    @BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    public void validateMandatoryOfficeTest()
    {
        office.addvalueinMandatoryField("TestOfficeAutomation","Default");
    }

}
