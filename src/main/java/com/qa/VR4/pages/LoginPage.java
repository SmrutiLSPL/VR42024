package com.qa.VR4.pages;

import com.qa.VR4.constants.AppConstants;
import com.qa.VR4.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    //1. private By Locators - page locators
    private By emailAddress = By.name("Users[email]");
    private By NextBtn = By.xpath("//a[text()=' Next ']");
    private By password = By.id("users-password");
    private By selectdrp = By.name("userlayout");
    private By submitbtn = By.id("loginSubmitBtn");
    private By forgotlink = By.linkText("Forgot Password?");
    private By ownsignlink = By.linkText("Owner Sign In");
    private By ErrorMsg = By.xpath("//div[@class='toast-message']");

    //2. public page Constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3.  public page actoin/Methos

    public String getLoginPageTitle() {
        String actTitle = driver.getTitle();
        System.out.println(actTitle);
        return actTitle;
    }

    public String getLoginPageURL() {
        String actURL = driver.getCurrentUrl();
        System.out.println(actURL);
        return actURL;
    }

    public boolean IsForgotpwdLinkExist() {
        return driver.findElement(forgotlink).isDisplayed();
    }

    public boolean IsOwnSignlinkExist() {
        return driver.findElement(ownsignlink).isDisplayed();
    }


    public String VRloginForNegative(String username, String  pwd, String visibleText) throws InterruptedException {
        System.out.println("App creds are : " + username + ":" + pwd);
        eleUtil.doSendKeys(emailAddress, username);
        eleUtil.doClick(NextBtn);
        Thread.sleep(2000);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doSelectDropDownByVisibleText(selectdrp, visibleText);
        eleUtil.doClick(submitbtn);

        String actErrorMsg = driver.findElement(ErrorMsg).getText();
        System.out.println(actErrorMsg);
        return actErrorMsg;

    }


    public void VRlogin(String username, String  pwd, String visibleText) throws InterruptedException {
        System.out.println("App creds are : " + username + ":" + pwd);
        eleUtil.doSendKeys(emailAddress, username);
        eleUtil.doClick(NextBtn);
        Thread.sleep(2000);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doSelectDropDownByVisibleText(selectdrp, visibleText);
        eleUtil.doClick(submitbtn);

    }

}
