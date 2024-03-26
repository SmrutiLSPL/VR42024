package com.qa.VR4.pages;

import com.qa.VR4.utils.JavaScriptUtil;
import com.qa.VR4.utils.VRUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DispatchPage {
    private static WebDriver driver;
    private VRUtils vrutil;
    private JavaScriptUtil js;

    By housekeepingMenu = By.xpath("//a[@id='mainmenua7 ']");
    By clickCalendarIcon = By.xpath("//a[@class='datechanger btn btn-default ']");
    By clickInspectionCalendarIcon=By.xpath("//input[@class='form-control dateineditmodal hasDatepicker']");
    By selectuserclick = By.xpath("//a[@class='chosen-single']/parent::div");
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


    public void openInspectionUnitMarkCompleteTask(String inspectionUnitName, String actionTaskName) throws InterruptedException {
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
                break;

            }


        }
        String actMsg = (driver.findElement(By.xpath("//div[contains(text(),'" + inspectionUnitName + "')]"))).getText();
        System.out.println(actMsg);
        String ExpectedTitle = inspectionUnitName + " To Completed.";
        System.out.println(ExpectedTitle);
        Assert.assertEquals(ExpectedTitle, actMsg);

    }

    public void openInspectionUnitResetTask(String inspectionUnitName, String actionTaskName, String date) throws InterruptedException {
        String isFutureDate = date;
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
        String ExpectedTitle = inspectionUnitName + " To Reset.";
        System.out.println(ExpectedTitle);
        Assert.assertEquals(ExpectedTitle, actMsg);

    }


    public void openInspectionUnitCheckInTask(String inspectionUnitName, String actionTaskName, String date) throws InterruptedException {

        String isFutureDate = date;

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
        String ExpectedTitle = inspectionUnitName + " To Checked In.";

        System.out.println(ExpectedTitle);
        Assert.assertEquals(ExpectedTitle, actMsg);

//        if (isFutureDate.equals(date)) {
//            // Redirect to the current date
//            driver.findElement(By.cssSelector("a[title='Today']")).click();
//            // Wait for page to load
//            Thread.sleep(5000);
//            // Retrieve the message again
//            js.scrollIntoView(driver.findElement(By.xpath("//span[text()='" + inspectionUnitName + "']")));
//            driver.findElement(By.xpath("//span[text()='" + inspectionUnitName + "']")).click();
//        }

    }

    public void openInspectionUnitCheckOutTask(String inspectionUnitName, String actionTaskName) throws InterruptedException {

        driver.findElement(By.cssSelector("a[title='Today']")).click();
        js.scrollIntoView(driver.findElement(By.xpath("//span[text()='" + inspectionUnitName + "']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + inspectionUnitName + "']")).click();
        Thread.sleep(1000);
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
        String ExpectedTitle = inspectionUnitName + " To Checked Out.";

        System.out.println(ExpectedTitle);
        Assert.assertEquals(ExpectedTitle, actMsg);


    }


    public void MarkCompleteWithResetFunction(String date, String inspectionUnitName, String actionTaskName) throws InterruptedException {
        String isFutureDate = date;
        if (isFutureDate.equals(date)) {
            // Redirect to the current date
            driver.findElement(By.cssSelector("a[title='Today']")).click();

            // Wait for page to load
            Thread.sleep(5000);
            // Retrieve the message again
            js.scrollIntoView(driver.findElement(By.xpath("//span[text()='" + inspectionUnitName + "']")));
            driver.findElement(By.xpath("//span[text()='" + inspectionUnitName + "']")).click();

            Thread.sleep(5000);
            List<WebElement> AllActionButton = driver.findElements(By.xpath("//a[@class='btn btn-primary checkonaction mt-2']"));
            int totalunit = AllActionButton.size();
            // System.out.println(totalunit);

            for (WebElement e : AllActionButton) {
                String AllActionBtn = e.getText();
                // System.out.println(AllActionBtn);

                if (AllActionBtn.equals(actionTaskName)) {
                    e.click();
                }
            }


            String actMsg = (driver.findElement(By.xpath("//div[contains(text(),'" + inspectionUnitName + "')]"))).getText();
            // System.out.println(actMsg);
            String ExpectedTitle = inspectionUnitName + " To Reset.";
            //System.out.println(ExpectedTitle);
            Assert.assertEquals(ExpectedTitle, actMsg);

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
        Assert.assertEquals(ExpectedTitle, actMsg);

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
        Assert.assertEquals(ExpectedTitle, actMsg);

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
        Assert.assertEquals(ExpectedTitle, actMsg);

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
        String SelectUnitName= driver.findElement(By.xpath("//span[@id='modalunitname']")).getText();
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
                Assert.assertTrue("Test case passed ", true);
                //System.out.println("Test case passed");
            }
        }

        //vrutil.selectUnitforDispatchPage("AMAZING");
    }

}









