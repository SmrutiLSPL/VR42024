package com.qa.VR4.pages.GL;

import com.qa.VR4.utils.JavaScriptUtil;
import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class globalConfigPage {

    private WebDriver driver;
    private VRUtils vrutil;
    private JavaScriptUtil js;
    //1. Private by locators - page locators


    public globalConfigPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        js = new JavaScriptUtil(driver);
    }


    public void scrollSunMenu(int timeout, String submenuName) throws InterruptedException {
        Thread.sleep(2000);
            js.scrollIntoView(driver.findElement(By.xpath("//span[text()='" + submenuName + "']")));
        vrutil.waitforMenuElementVisible(timeout,submenuName);
    }


}
