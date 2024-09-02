package com.qa.VR4.pages.HousekeepingMenu;

import com.qa.VR4.utils.JavaScriptUtil;
import com.qa.VR4.utils.VRUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class DispatchPage {
    private static WebDriver driver;
    private VRUtils vrutil;
    private JavaScriptUtil js;

    By housekeepingMenu = By.xpath("//a[@id='mainmenua7 ']");
    By clickCalendarIcon = By.xpath("//a[@class='datechanger btn btn-default ']");
    By clickInspectionCalendarIcon = By.xpath("//input[@class='form-control dateineditmodal hasDatepicker']");
    By selectuserclick = By.xpath("//div[@class='chosen-container chosen-container-single chosen-container-active']");
    By userInputtxt = By.xpath("//input[@autocomplete='off']");

    By saveClick = By.xpath("//a[text()='Save']");

    public DispatchPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        js = new JavaScriptUtil(driver);
    }

    public void clickOnInspectionMenu() throws InterruptedException {

        vrutil.doClick(housekeepingMenu);
    }

    public void clickOnDispatchListOfMenu() {
        vrutil.clickMaintenanceMenuWhenReady("Dispatch", 5);
    }

    public void openDatePicker() {
        vrutil.doClick(clickCalendarIcon);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));

    }


    public void openInspectionUnit(String inspectionUnitName) {
        // Thread.sleep(5000);
        List<WebElement> unitName = driver.findElements(By.xpath("//div[@class='eventdataholder']/parent::div[@eventtype='insp']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        boolean unitFound = false;  // Flag to track if the unit is found
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(inspectionUnitName)) {
                e.click();
                break;
            }
            if (!unitFound) {
                // Handle the case where the unit is not found
                System.out.println("Unit '" + inspectionUnitName + "' not found.");
                // You can throw an exception, log an error, or handle it as per your application's needs
                // For example, you might throw an exception:
                throw new NoSuchElementException("Unit '" + inspectionUnitName + "' not found.");
            }

        }

    }

    public void openHousekeepingUnit(String HousekeepingUnitName) throws InterruptedException {
        // Thread.sleep(5000);

        List<WebElement> unitName = driver.findElements(By.xpath("//span[normalize-space()!='LP'][normalize-space()!='LD']/parent::div[@class='eventdataholder']/parent::div[@eventtype='hk']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(HousekeepingUnitName)) {
                e.click();
                break;
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='actionmodel']//button[@type='button'][normalize-space()='×']")).click();

    }

    public void openLaundryTask(String HousekeepingUnitName,String lName)
    {


        List<WebElement> unitName = driver.findElements(By.xpath("//span[normalize-space()!='LP'][normalize-space()!='LD']/parent::div[@class='eventdataholder']/parent::div[@eventtype='hk']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(HousekeepingUnitName)) {
                driver.findElement(By.xpath("//span[text()='"+lName+"']")).click();
                break;
            }

        }
        driver.switchTo().alert().dismiss();


    }

    /*
      Action task method for  housekeeping and Inspection
     */
    public void taskActionForDispatchtask(String actionName) {
        driver.findElement(By.xpath("//div[@class='text-center ']//a[text()='" + actionName + "']")).click();
    }

    /*
     Select user for  housekeeping and Inspection
    */
    public void selectUser(String hkusername) {
        vrutil.doClick(selectuserclick);
        vrutil.doSendKeys(userInputtxt, hkusername);
        List<WebElement> Alluser = driver.findElements(By.xpath("//ul[@class='chosen-results']//li"));
        int totalunit = Alluser.size();
        //System.out.println(totalunit);

        for (WebElement e : Alluser) {
            hkusername = e.getText();
            System.out.println(hkusername);

            if (hkusername.equals(Alluser)) {
                e.click();
                break;

            }

        }

    }


    public void openInspectionUnitHoldTask(String inspectionUnitName, String actionTaskName) throws InterruptedException {

        Thread.sleep(5000);
        List<WebElement> unitName = driver.findElements(By.xpath("//div[@class='eventdataholder']/parent::div[@eventtype='insp']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(inspectionUnitName)) {
                e.click();
                break;
            }
        }

        Thread.sleep(5000);
        List<WebElement> AllActionButton = driver.findElements(By.xpath("//a[@class='btn btn-primary checkonaction mt-2']"));
        int totalunit = AllActionButton.size();
        System.out.println(totalunit);

        for (WebElement e : AllActionButton) {
            String AllActionBtn = e.getText();
            System.out.println(AllActionBtn);

            if (AllActionBtn.equals(actionTaskName)) {
                e.click();
            }
        }
        String actMsg = (driver.findElement(By.xpath("//div[contains(text(),'" + inspectionUnitName + "')]"))).getText();
        System.out.println(actMsg);
        String ExpectedTitle = inspectionUnitName + " To Hold.";
        System.out.println(ExpectedTitle);
        //  Assert.assertEquals(ExpectedTitle, actMsg);

    }

    public void openInspectionUnitHoldWithReleaseTask(String inspectionUnitName, String actionTaskName) throws InterruptedException {

        Thread.sleep(5000);
        List<WebElement> unitName = driver.findElements(By.xpath("//div[@class='eventdataholder']/parent::div[@eventtype='insp']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(inspectionUnitName)) {
                e.click();
                break;
            }
        }

        Thread.sleep(5000);
        List<WebElement> AllActionButton = driver.findElements(By.xpath("//a[@class='btn btn-primary checkonaction mt-2']"));
        int totalunit = AllActionButton.size();
        System.out.println(totalunit);

        for (WebElement e : AllActionButton) {
            String AllActionBtn = e.getText();
            System.out.println(AllActionBtn);

            if (AllActionBtn.equals(actionTaskName)) {
                e.click();
            }
        }
        String actMsg = (driver.findElement(By.xpath("//div[contains(text(),'" + inspectionUnitName + "')]"))).getText();
        System.out.println(actMsg);
        String ExpectedTitle = inspectionUnitName + " To Release.";
        System.out.println(ExpectedTitle);
        //Assert.assertEquals(ExpectedTitle, actMsg);

    }

    public void openInspectionUnitHoldWithResetHoldReleaseTask(String inspectionUnitName, String actionTaskName) throws InterruptedException {

        Thread.sleep(5000);
        List<WebElement> unitName = driver.findElements(By.xpath("//div[@class='eventdataholder']/parent::div[@eventtype='insp']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(inspectionUnitName)) {
                e.click();
                break;
            }
        }

        Thread.sleep(5000);
        List<WebElement> AllActionButton = driver.findElements(By.xpath("//a[@class='btn btn-primary checkonaction mt-2']"));
        int totalunit = AllActionButton.size();
        System.out.println(totalunit);

        for (WebElement e : AllActionButton) {
            String AllActionBtn = e.getText();
            System.out.println(AllActionBtn);

            if (AllActionBtn.equals(actionTaskName)) {
                e.click();
            }
        }
        String actMsg = (driver.findElement(By.xpath("//div[contains(text(),'" + inspectionUnitName + "')]"))).getText();
        System.out.println(actMsg);
        String ExpectedTitle = inspectionUnitName + " To Hold Reset.";
        System.out.println(ExpectedTitle);
        // Assert.assertEquals(ExpectedTitle, actMsg);

    }

    public void selectNewInspectionUser(String userName) throws InterruptedException {
        Thread.sleep(5000);
        vrutil.doClick(selectuserclick);
        driver.findElement(By.xpath("//li[text()='" + userName + "' and @class='active-result']")).click();
        vrutil.doClick(saveClick);
        Thread.sleep(5000);
        String ExpectedUsername = driver.findElement(By.xpath("//div[@class='inspectordiv'] [contains(text(),'" + userName + "')]")).getText();
        //System.out.println("ExpectedUsername " + ExpectedUsername );
        if (userName.equals(ExpectedUsername)) {
            System.out.println("Test Case Passed ");
        } else {
            System.out.println("Test Case Failed  ");
        }

    }


    public void selectNewDateForInspectionTaskAndValidateWithNewDate() throws InterruptedException {
        Thread.sleep(2000);
        String SelectUnitName = driver.findElement(By.xpath("//span[@id='modalunitname']")).getText();
        System.out.println(SelectUnitName);
        Thread.sleep(2000);
        vrutil.doClick(clickInspectionCalendarIcon);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));
        vrutil.selectDate("21", "MARCH", "2024");
        Thread.sleep(1000);
        vrutil.doClick(saveClick);
        Thread.sleep(1000);
        vrutil.doClick(clickCalendarIcon);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));
        vrutil.selectDate("21", "MARCH", "2024");

        List<WebElement> unitName = driver.findElements(By.xpath("//div[@class='eventdataholder']/parent::div[@eventtype='insp']/descendant::span[@class='unit_code_label']"));
        //int totalunit= unitName.size();
        //System.out.println(totalunit);
        for (WebElement e : unitName) {
            String AllunitName = e.getText();
            //System.out.println(AllunitName);

            if (AllunitName.equals(SelectUnitName)) {
                //System.out.println("Hi");
                //Assert.assertTrue(true, "Test case passed ");
                //System.out.println("Test case passed");
            }
        }

        //vrutil.selectUnitforDispatchPage("AMAZING");
    }

}









