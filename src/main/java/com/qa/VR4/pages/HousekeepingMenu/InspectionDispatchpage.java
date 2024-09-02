package com.qa.VR4.pages.HousekeepingMenu;

import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class InspectionDispatchpage {

    private WebDriver driver;
    private VRUtils vrutil;
    //1. Private By locators - Page locators
    By continuebtn = By.xpath("//button[contains(@class,'continue')]");
    By entersearchtxt = By.xpath("//input[@id='searchguest']");
    By Clicksearch = By.xpath("//input[@id='searchguest']");
    By clickReserve = By.xpath("//button[@class='btn btn-primary continue']");
    By bookingValue = By.xpath("//tr[position()=4]//td[position()=2]//a");

    By clickonfolio = By.xpath("//a[normalize-space()='Folio']");


    //2. Public page Constructor
    public InspectionDispatchpage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
    }



    //3. Public Page actions/methods

    public void createReservation(String username) throws InterruptedException {

        vrutil.doClick(Clicksearch);
        vrutil.doSendKeys(entersearchtxt, username);
        Thread.sleep(5000);
        WebElement name = driver.findElement(By.xpath("//span[contains(text(),'" + username + "')]/ancestor::div[contains(@id,'ui-id')]"));
        new Actions(driver)
                .click(name)
                .perform();
        vrutil.doClick(clickReserve);
        Thread.sleep(5000);
    }


}
