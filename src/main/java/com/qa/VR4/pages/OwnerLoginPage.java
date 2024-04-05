package com.qa.VR4.pages;

import com.qa.VR4.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OwnerLoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    //1. private By Locators - page locators
    private By emailAddress = By.name("Users[email]");
    private By NextBtn = By.xpath("//a[text()=' Next ']");
    private By password = By.id("users-password");

    private By submitbtn = By.id("loginSubmitBtn");
    private By forgotlink = By.linkText("Forgot Password?");
    private By ownsignlink = By.xpath("//a[text()='Owner Sign In']");
    private By ErrorMsg = By.xpath("//div[@class='toast-message']");

    //2. public page Constructor

    public OwnerLoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String VROwnerLogin(String username, String pwd) throws InterruptedException {
        System.out.println("App creds are : " + username + ":" + pwd);
        eleUtil.doClick(NextBtn);
        Thread.sleep(500);
        eleUtil.doClick(ownsignlink);
        Thread.sleep(500);
        eleUtil.doSendKeys(emailAddress, username);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(submitbtn);
        String acttitle = driver.getTitle();
        return acttitle;
    }

    public String VROwnerWithAllInvaliddata(String username, String pwd) throws InterruptedException {
        System.out.println("App creds are : " + username + ":" + pwd);
        eleUtil.doClick(NextBtn);
        Thread.sleep(500);
        eleUtil.doClick(ownsignlink);
        Thread.sleep(1000);
        eleUtil.doSendKeys(emailAddress, username);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(submitbtn);
        Thread.sleep(1000);
        String actErrorMsg = driver.findElement(ErrorMsg).getText();
        System.out.println(actErrorMsg);
        //return actErrorMsg;

        return actErrorMsg;
    }

}
