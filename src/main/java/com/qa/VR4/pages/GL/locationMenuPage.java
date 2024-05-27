package com.qa.VR4.pages.GL;

import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class locationMenuPage {
    private WebDriver driver;
    private VRUtils vrutil;
    private JavascriptExecutor executor;
    private WebDriverWait wait;
    private Actions act;

    protected By addBtn = By.xpath("//a[@class='newLocation btn btn-primary']");
    protected By locationName = By.name("name");
    protected By clickarea = By.xpath("//a[@class='btn btn-primary btn-sm btn-icon addArea']");
    protected By areaName = By.name("vb_location_areas[][name]");
    protected By clickresort = By.xpath("//a[@class='btn btn-primary btn-sm btn-icon addRescomm']");
    protected By addresort = By.xpath("//span[@role='combobox']");
    protected By deleteresort = By.xpath("//a[contains(@class,'fakeDeleteRescomm')]");
    protected By deleteArea = By.xpath("//a[contains(@class,'fakeDeleteArea')]");
    protected By savebtn = By.xpath("//a[text()='Save']");
    protected By deletebtn = By.xpath("//a[text()='Delete']");

    protected By ltext = By.xpath("//input[@role='searchbox']");
    protected By llist = By.xpath("//ul[@id='select2-vb_location_rescommname-37-results']");
    protected By inputField = (By.xpath("//input[@class='select2-search__field']"));
    protected By close = By.xpath("//i[@class='far fa-arrow-right']");
    private String storedLocationName;

    public locationMenuPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        act = new Actions(driver);
        executor = (JavascriptExecutor) this.driver;
    }


    public String warningMsgValidate(int timeOut) throws InterruptedException {
        vrutil.doClick(addBtn);

        Thread.sleep(1000);
        vrutil.doClick(savebtn);

        String warningMsg = driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
        return warningMsg;

    }

    public String enterLocationWithValidData(int timeOut, String addLocationName, String arName) throws InterruptedException {
        vrutil.doClick(addBtn);
        Thread.sleep(1500);
        WebElement lName = vrutil.waitForElementVisible(locationName, timeOut);
        lName.sendKeys(addLocationName);
        vrutil.doClick(clickarea);
        vrutil.doSendKeys(areaName, arName);
        storedLocationName = addLocationName;
        return storedLocationName;

    }

    public void selectRestro(int timeOut, String restroname) throws InterruptedException {
        vrutil.doClick(clickresort);
        // Wait for the list of restaurants to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement resCommList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='res-comm-add-select flex-grow-1 me-3']")));

        // Click on the restaurant selection input
        resCommList.click();

        // Locate the input field
        WebElement inputField = driver.findElement(By.xpath("//input[@class='select2-search__field']"));

        // Enter the restaurant name
        inputField.sendKeys(restroname);

        // Wait for the list of restaurants to appear
        List<WebElement> restrolist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[contains(@class,'select2-results__options')]//li")));

        // Iterate through the list of restaurants and select the matching one
        for (WebElement rlist : restrolist) {

            if (rlist.getText().equals(restroname)) {
                rlist.click(); // Click on the matching restaurant
                vrutil.doClick(savebtn);
                break;
            }

        }

        Thread.sleep(1500);
        vrutil.doClick(close);

    }

    public String validateLocationName(int timeOut) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(1500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        List<WebElement> locationlist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='panelholder']/descendant::div[@class='singleRowName'][position()!=1]")));
        //   System.out.println("Number of locations found: " + locationlist.size());

        for (WebElement e : locationlist) {
            String locationname = e.getText();
            // System.out.println(locationname);
            if (locationName.equals(storedLocationName.trim())) { // Trim to remove leading/trailing whitespace
                break;
            }
        }
        // System.out.println(storedLocationName);
        return storedLocationName;

    }

    public void deleteLocationName(int timeOut, String searchLocationName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        List<WebElement> locationlist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='panelholder']/descendant::div[@class='singleRowName'][position()!=1]")));
        //   System.out.println("Number of locations found: " + locationlist.size());

        for (WebElement e : locationlist) {
            String locationname = e.getText().trim(); // Trim to remove leading/trailing whitespace
            System.out.println(locationname);

            if (locationname.equals(searchLocationName)) {
                e.click();
                Thread.sleep(1500);
                vrutil.doClick(deletebtn);
            }
        }
        // System.out.println(storedLocationName);
    }

    public String editLocationName(int timeOut, String searchLocationName, String addLocationName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        List<WebElement> locationlist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='panelholder']/descendant::div[@class='singleRowName'][position()!=1]")));
        //   System.out.println("Number of locations found: " + locationlist.size());

        for (WebElement e : locationlist) {
            String locationname = e.getText().trim(); // Trim to remove leading/trailing whitespace
            System.out.println(locationname);

            if (locationname.equals(searchLocationName)) {
                e.click();
                WebElement lName = vrutil.waitForElementVisible(locationName, timeOut);
                lName.clear();
                lName.sendKeys(addLocationName);
                vrutil.doClick(savebtn);
            }
        }
        // System.out.println(storedLocationName);
        return addLocationName;
    }


}
