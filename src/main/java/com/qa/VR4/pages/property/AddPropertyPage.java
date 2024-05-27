package com.qa.VR4.pages.property;

import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class AddPropertyPage {

    private WebDriver driver;
    private VRUtils vrutil;

    private static JavascriptExecutor executor;

    //1. Private by locators - page locators
    private By reservationMenu = By.xpath("//a[@id='mainmenua9 ']");
    private By addUnit = By.xpath("//a[contains(@class,'btn-primary')]");
    private By searchUnit = By.xpath("//input[@name='unit_code']");
    private By clickUnitname = By.xpath("//p[@class='unitname']");
    private By addUnitBtn = By.xpath("//a[@class='btnulti btn btn-primary me-1']");

    //---------------------Summary form------------------------------>
    private By unitcodetxt = By.xpath("//input[@id='unit-code']");
    private By unitnametxt = By.xpath("//input[@id='name']");
    private By unitnotes = By.id("unit-notes");
    private By maeketingtxt = By.id("headline");
    private By descriptiontxt = By.id("long-description-noscrollbars");

    //---------------------End Summary form------------------------------>
    //---------------------Location  form------------------------------>
    private By locationdrp = By.xpath("//select[@name='vb_location_id']");
    private By officedrp = By.xpath("//select[@id='office-id']");
    private By taxdrp = By.xpath("//select[@id='taxdistrict-id']");
    private By addresstxt = By.id("address-line-1");
    private By address2txt = By.id("address-line-2");
    private By addverifiedcheck = By.id("address-verified");
    private By citytxt = By.id("city");
    private By statetxt = By.id("state");
    private By zipcodetxt = By.id("zip-code");
    private By latitudetxt = By.id("latitude"); // here I am not using Country dropdown
    private By longitudetxt = By.id("longitude");
    private By unitPhone1txt = By.id("unit-phone1");
    private By unitPhone2txt = By.id("unit-phone2");
    //--------------------- End Location  form------------------------------>
    //--------------------- Details  form------------------------------>
    private By area = By.name("vb_location_area_id");
    private By resortComm = By.name("locationrescomm_id");
    private By propertyType = By.name("property_type");
    private By bedrooms = By.name("details[bedrooms]");
    private By dathrooms = By.name("details[bathroom]");
    private By unitType = By.name("details[unit_type]");
    private By gulfFront = By.name("details[gulf_front]");
    private By stories = By.name("details[stories]");
    private By kabaEnabled = By.name("details[kaba_enabled]");
    private By keyCode = By.id("details-keycode");
    private By yearBuilt = By.id("details-year-built");
    private By dogsAllowed = By.id("details[dogs_allowed]");
    private By DOR1 = By.id("details-dor-1");
    private By DOR2 = By.id("details-dor-2");
    private By countyLicense = By.id("details-county-license");
    private By cityLicense = By.id("licensenumber");
    //Accommodations
    private By kingBeds = By.name("accommodations[kingbeds]");
    private By queenBeds = By.name("accommodations[queenbeds]");
    private By doubleFullBeds = By.name("accommodations[double_full_beds]");
    private By twinBeds = By.name("accommodations[single_twin_beds]");
    private By bunkBeds = By.name("accommodations[bunk_beds]");
    private By sleeperSofas = By.name("accommodations[sleeper_sofas]");
    private By otherBeds = By.name("accommodations[other_beds]");
    private By squareFeet = By.id("accommodations-square-feet");
    private By diningSeats = By.id("accommodations-dining-seats");
    private By amenityString = By.id("accommodations-amenity-string");
    private By maxOccupancy = By.id("sleep");
    private By housekeeping = By.id("accommodations-housekeeping");
    private By description = By.id("long-description");

    //--------------------- End Details  form------------------------------>
    //--------------------- Service  form------------------------------>
    private By serviceNotes = By.id("service-notes");

    //--------------------- End Service  form------------------------------>

    private By savebtn = By.xpath("//a[@class='btn btn-primary me-1 saveonly']");
    private By nectbtn = By.xpath("//div[@class='tab-content border border-top-0 p-4']//a[text()='Next']");

    //div[@class='tab-pane fade active show']/descendant::a[text()='Next']
    public AddPropertyPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        executor = (JavascriptExecutor) driver;
    }

    private static void clickElement(WebElement element) {

        executor.executeScript("arguments[0].click();", element);
    }


    public void clickmainmenu() throws InterruptedException {
        vrutil.doClick(reservationMenu);
    }

    public void property_Name(String unitName) {
        driver.findElement(By.xpath("//p[text()='" + unitName + "']")).click();
    }

    public String  openUnitBySearchBox(String unitName,int timeout) throws InterruptedException {
        vrutil.doClick(searchUnit);
        vrutil.doSendKeys(searchUnit, unitName);
        Thread.sleep(2000);
        vrutil.waitForElementVisible(clickUnitname,timeout).click();
        Thread.sleep(2000);
        String actTitle= driver.findElement(By.xpath("//div[@class='bd-highlight mb-3']")).getText();
        System.out.println(actTitle);
        return actTitle;
    }
    public void openAddUnitOption() {
        vrutil.doClick(addUnitBtn);

    }

    public void selecttab(String tabName, int timeOut) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'" + tabName + "')]"))).click();

    }
    /* Below method for Add unit with Save button action Negative scenario*/

    public int addUnitFormWithSaveButton(String unitcode, String unitName) {


        vrutil.doSendKeys(unitcodetxt, unitcode);
        vrutil.doSendKeys(unitnametxt, unitName);
        vrutil.doClick(savebtn);
        List<WebElement> allWarningNo = driver.findElements(By.xpath("//a[@class='nav-link' or  @class='nav-link active']/ancestor::fieldset[@class='panel']/descendant::span[@class='badge ml-2']"));
        int emptyMandatoryFieldsCount = 0;
        for (WebElement e : allWarningNo) {
            //System.out.println(e.getText());
            if (e.getText().isEmpty()) {
                emptyMandatoryFieldsCount++;
            }
        }
        int actualWarningsDisplayed = allWarningNo.size();
        return actualWarningsDisplayed;

//        if (emptyMandatoryFieldsCount != actualWarningsDisplayed) {
//            System.out.println("Validation failed: Incorrect number of warnings displayed.");
//            System.out.println("Expected: " + emptyMandatoryFieldsCount + " warnings, Actual: " + actualWarningsDisplayed + " warnings.");
//        } else {
//            System.out.println("Validation passed: Correct number of warnings displayed.");
//            System.out.println("Number of warnings displayed: " + actualWarningsDisplayed);
//        }

    }

    /*** Location Enter only mandate field Negative scenario */
    public int addUnitFormWithSaveButton(int timeOut, String locationName) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        /*
        location dropdown
         */
        WebElement locationdrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locationdrp)));
        locationdrp1.click();
        vrutil.doSelectDropDownValue(locationdrp, locationName);

        vrutil.doClick(savebtn);

        List<WebElement> allWarningNo = driver.findElements(By.xpath("//a[@class='nav-link' or  @class='nav-link active']/ancestor::fieldset[@class='panel']/descendant::span[@class='badge ml-2']"));
        int emptyMandatoryFieldsCount = 0;
        for (WebElement e : allWarningNo) {
            //System.out.println(e.getText());
            if (e.getText().isEmpty()) {
                emptyMandatoryFieldsCount++;
            }
        }
        int actualWarningsDisplayed = allWarningNo.size();
        return actualWarningsDisplayed;

        // driver.findElement(By.xpath("//a[text()='" + actiontbn + "']/parent::div"));

    }
    /* Add unit with next button code Negative scenario  */

    public String addUnitWithNextButton() throws InterruptedException {
        Thread.sleep(5000);
        vrutil.doClick(nectbtn);
        String actMsg = driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
        return actMsg;
    }

    // Write core for summary All field with positive scenario

    public void addUnitsummaryFormWithSaveButton(String ucode, String uname, String maeketing, String description, String unote, int timeOut) throws InterruptedException {

        vrutil.doSendKeys(unitcodetxt, ucode);
        vrutil.doSendKeys(unitnametxt, uname);
        vrutil.doSendKeys(unitnotes, unote);
        vrutil.doSendKeys(maeketingtxt, maeketing);
        vrutil.doSendKeys(descriptiontxt, description);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nectbtn));
        nextButton.click();


    }


    public void addUnitFormWithSaveButton(int timeOut, String locationName, String officeName, String taxName, String addressvalue, String address2value, String statevalue, String cityvalue, String zipvalue, String latitudeValue, String longitudeValue, String unitPhone1value, String unitPhone2value) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        /* location dropdown*/
        WebElement locationdrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locationdrp)));
        locationdrp1.click();
        vrutil.doSelectDropDownValue(locationdrp, locationName);

        /* Office  dropdown*/
        WebElement Officedrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(officedrp)));
        Officedrp1.click();
        vrutil.doSelectDropDownValue(officedrp, officeName);

        /* Office  dropdown*/
        WebElement taxDistrict1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(taxdrp)));
        taxDistrict1.click();
        vrutil.doSelectDropDownValue(taxdrp, taxName);

        /*Address 1,Address 2   cityvalue,State,Zip Code,Unit Phone 1,Unit Phone 2*/
        vrutil.doSendKeys(addresstxt, addressvalue);
        vrutil.doSendKeys(address2txt, address2value);
        vrutil.doSendKeys(citytxt, cityvalue);
        vrutil.doSendKeys(statetxt, statevalue);
        vrutil.doSendKeys(zipcodetxt, zipvalue);
        vrutil.doSendKeys(latitudetxt, latitudeValue);
        vrutil.doSendKeys(longitudetxt, longitudeValue);
        vrutil.doSendKeys(unitPhone1txt, unitPhone1value);
        vrutil.doSendKeys(unitPhone2txt, unitPhone2value);
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='tab-content border border-top-0 p-4']//a[text()='Next']"));
        clickElement(searchButton);

    }

    public String addUnitWithDetailsTab(int timeOut, String areaName, String resortName, String propertyName, String maxvalue) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        /* location dropdown*/
        WebElement areadrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(area)));
        areadrp1.click();
        vrutil.doSelectDropDownValue(area, areaName);

        WebElement resortdrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(resortComm)));
        resortdrp1.click();
        vrutil.doSelectDropDownValue(resortComm, resortName);

        WebElement propertydrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(propertyType)));
        propertydrp1.click();
        vrutil.doSelectDropDownValue(propertyType, propertyName);
        vrutil.doSendKeys(maxOccupancy, maxvalue);

        vrutil.doClick(savebtn);
        Thread.sleep(2000);
        String SuccessMsg = driver.findElement(By.xpath("//h4[text()='Success! Unit Created.']")).getText();
        return SuccessMsg;

    }
    public String validateLocationMenuWithNewLocationName(int timeOut, String locationName)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement locationdrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locationdrp)));
        locationdrp1.click();
        String actDrp= vrutil.doSelectDropDownValuewithreturn(locationdrp, locationName);
        System.out.println("New name "+actDrp);
        return actDrp;


    }
    public Boolean validateLocationMenuWithdeleteLocationName(int timeOut, String locationName)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement locationdrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locationdrp)));
        locationdrp1.click();
        Boolean actDrp= Boolean.valueOf(vrutil.doSelectDropDownValuewithreturn(locationdrp, locationName));
        System.out.println("New name "+actDrp);
        return actDrp;


    }




}
