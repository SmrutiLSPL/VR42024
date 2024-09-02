package com.qa.VR4.pages.HousekeepingMenu;

import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ReservationToInspectionPage {

    private WebDriver driver;
    private VRUtils vrutil;
    private JavascriptExecutor executor;
    // 1. private By Locators - page locators
    By reservationMenu=By.xpath("//a[@id='mainmenua9 ']");
    By housekeepingMenu=By.xpath("//a[@id='mainmenua7 ']");
    By clickCalendarIcon = By.xpath("//a[@class='datechanger btn btn-default ']");
    // 2. public page Constructor
    public ReservationToInspectionPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        executor = (JavascriptExecutor) this.driver;
    }
    // 3. public page actoin/Methos
    public void clickmainmenu() throws InterruptedException {
        vrutil.doClick(reservationMenu);
    }
    public void clickOnListOfMenu()
    {
        vrutil.clickMaintenanceMenuWhenReady("Reservation Grid", 5);
    }

}
