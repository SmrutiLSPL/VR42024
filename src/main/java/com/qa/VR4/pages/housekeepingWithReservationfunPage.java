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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class housekeepingWithReservationfunPage {

    private WebDriver driver;
    private VRUtils vrutil;
    private JavaScriptUtil js;

    //2. Public page Constructor
    public housekeepingWithReservationfunPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        js = new JavaScriptUtil(driver);
    }

    By housekeepingMenu = By.xpath("//a[@id='mainmenua7 ']");
    By clickCalendarIcon = By.xpath("//a[@class='datechanger btn btn-default ']");
    By selectuserclick = By.xpath("//a[@class='chosen-single']/parent::div");
    By saveClick = By.xpath("//a[text()='Save']");

    public void clickOnHousekeepingMenu() throws InterruptedException {

        vrutil.doClick(housekeepingMenu);
    }

    public void clickOnDispatchListOfMenu() {
        vrutil.clickMaintenanceMenuWhenReady("Dispatch", 5);
    }

    public void openDatePicker() {
        vrutil.doClick(clickCalendarIcon);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));

    }

    public void selectNewHousekeeperUser(String userName) throws InterruptedException {
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
    public void validateCurrentdate(String enterUnitNAme,String fdate,String fmonth, String fyear) throws InterruptedException {
        WebElement dateElement = driver.findElement(By.xpath("//td[@class='datetd']"));

        String dateText = dateElement.getText();
        System.out.println(dateText);

// Splitting the string by "-"
        String[] parts = dateText.split("-");
// Accessing the date part and trimming any leading or trailing spaces
        String datePart = parts[1].trim();
        System.out.println("Date: " + datePart);
        // Parsing the displayed date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate displayedDate = LocalDate.parse(datePart, formatter);

// Getting the current date
        LocalDate currentDate = LocalDate.now();

// Checking if the current date matches the displayed date
        if (currentDate.equals(displayedDate) && !currentDate.equals(displayedDate)) {
            System.out.println("Date validation successful!");
            Thread.sleep(5000);
            vrutil.selectUnitforDispatchPage(enterUnitNAme);
        } else {
            System.out.println("Date validation failed! Redirecting...");
            Thread.sleep(5000);
            vrutil.doClick(clickCalendarIcon);
            vrutil.selectDate(fdate, fmonth, fyear);
            vrutil.selectUnitforDispatchPage(enterUnitNAme); // Remove quotes to use the variable
            // Perform redirection here, for example:
            // driver.get("http://yourredirecturl.com");
        }


    }


}

