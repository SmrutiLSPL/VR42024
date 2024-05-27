package com.qa.VR4.tests.propertyTest;

import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class editHomePageTest extends BaseTest {

    @BeforeTest
    public void login() {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    @Test(priority = 1)
    public void OpenPropertyMenu() throws InterruptedException {
        addProperty.clickmainmenu();
        vrutil.clickMaintenanceMenuWhenReady("Properties", 5);
    }
    @Test(priority = 2)
    public void validateEditUnitName() throws InterruptedException {
        //addProperty.openUnitBySearchBox("205 Sand Oak Blvd",10);
        editProperty.editUnitNameInExsitingUnit(10,"205 Sand Oak Blvd","205SAND123");
        editProperty.goBack();

    }
    @Test(priority = 3)
    public void SearchUnitFromSearchBox()throws InterruptedException
    {
        String actTitle=addProperty.openUnitBySearchBox("205 Sand Oak Blvd",15);
        actTitle.trim();
        String expUnitname= editProperty.expexctedUnit();
        Assert.assertEquals(actTitle,expUnitname,"Data not found");
        editProperty.goBack();
    }
    @Test(priority = 4)
    public void validateBedDrp() throws InterruptedException {

        //editProperty.selectArea(10, "Select Area");
        String selectedBed = editProperty.selectBed("1");

        // Verify if the selected area matches the expected area name
        Assert.assertEquals("1", selectedBed, "Selected Bedroom does not match the expected Bedroom.");
        editProperty.restPropertyFilter();
    }

    @Test(priority =5)
    public void validateAreaDrp()
    {
       
        String selectedArea = editProperty.selectArea(10, "30-A");

        // Verify if the selected area matches the expected area name
        Assert.assertEquals(selectedArea, "30-A", "Selected area does not match the expected area.");
    }





}
