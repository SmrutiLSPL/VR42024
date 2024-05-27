package com.qa.VR4.pages.property;

import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EditPropertyPage {
    private WebDriver driver;
    private VRUtils vrutil;
    private static JavascriptExecutor executor;
    //1. Private by locators - page locators
    private By areadrp = By.xpath("//select[@name='area']");
    private By bedtxt = By.xpath("//input[@name='bedroom_loft']");
    private By searchBtn = By.xpath("//button[contains(@value,'Search')]");
    private By gobackBtn = By.xpath("//a[text()='Go Back']");
    private By searchUnit = By.xpath("//input[@name='unit_code']");
    private By clickUnitname = By.xpath("//p[@class='unitname']");

    /* Edit unit page locators */
    private By unitname = By.xpath("//div[@class='bd-highlight mb-3']/child::h4");
    private By resetbtn = By.xpath("//button[@value='Reset']");
    private By unitcodetxt = By.id("unit-code");
    private By saveBtn = By.xpath("//a[@class='btn btn-primary saveonly']");
    private By locationdrp = By.xpath("//select[@name='vb_location_id']");


    public EditPropertyPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        executor = (JavascriptExecutor) driver;
    }

    public void goBack() {
        vrutil.doClick(gobackBtn);
    }

    public String selectArea(int timeOut, String areaName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        /* location dropdown*/

        WebElement areandrp1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(areadrp)));
        areandrp1.click();
        vrutil.doSelectDropDownValue(areadrp, areaName);
        vrutil.doClick(searchBtn);

        List<WebElement> AllAreaList = driver.findElements(By.xpath("//div[@class='searchtext areasearch graytextforunitlist']"));
        for (WebElement arealist : AllAreaList) {
            String areaNm = arealist.getText();

            if (areaName.equals(areaNm)) {
                return areaName;
            }
        }
        System.out.println("Warning: Provided area name not found in the list.");

        return null;
    }

    public void restPropertyFilter() {
        vrutil.doClick(resetbtn);
    }

    public String selectBed(String bedNumber) throws InterruptedException {

        vrutil.doSendKeys(bedtxt, bedNumber);
        vrutil.doClick(searchBtn);
        List<WebElement> AllBedList = driver.findElements(By.xpath("//div[@class='searchtext bedsearch graytextforunitlist']"));
        for (WebElement bedList : AllBedList) {
            String NoBed = bedList.getText();
            if (bedNumber.equals(NoBed)) {
                return bedNumber;
            }
        }
        System.out.println("Warning: Provided Bed room not found in the list.");

        return null;
    }

    public String expexctedUnit() {
        String uname = driver.findElement(unitname).getText();
        return uname;
    }

    /*
    Edit unit name and search with new unit name
     */
    public void editUnitNameInExsitingUnit(int timeout, String unitName, String NewUnitName) throws InterruptedException {
        vrutil.doSendKeys(searchUnit, unitName);
        Thread.sleep(2000);
        vrutil.waitForElementVisible(clickUnitname, timeout).click();
        Thread.sleep(2000);
        driver.findElement(unitcodetxt).clear();
        vrutil.doSendKeys(unitcodetxt, NewUnitName);
        vrutil.doClick(saveBtn);

    }


}
