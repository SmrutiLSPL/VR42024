package com.qa.VR4.pages;

import com.qa.VR4.constants.AppConstants;
import com.qa.VR4.utils.JavaScriptUtil;
import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CreateUserPage {

    private WebDriver driver;
    private VRUtils vrutil;
    private JavaScriptUtil js;

    private By newbtn = By.xpath("//a[@class='btn btn-primary newbtn']");
    private By userPageTitle = By.xpath("//h4[normalize-space()='Users']");
    private By Usergrp = By.xpath("//ul[@class='chosen-choices']//input");
    private By userName = By.id("users-username");
    private By firstName = By.id("users-first-name");
    private By lastName = By.id("users-last-name");
    private By cellPhone = By.id("users-user_detail-cellphone");
    private By emailID = By.id("users-email");
    private By password = By.id("users-password");
    private By CPassword = By.id("users-cpassword");
    private By addUserbtn = By.id("addUserSubmitBtn");
    private By cancelbtn = By.xpath("//a[text()='Cancel']");
    private By sucessMsg = By.xpath("//div[@class='toast-message']");
    private By masterSearch = By.id("navbarForm");


    //**** Error msg *****
    By userError = By.xpath("//div[contains(text(),'Please enter username')]");
    By firstNError = By.xpath("//div[contains(text(),'Please enter first name')]");
    By emailError = By.xpath("//div[contains(text(),'Please enter email')]");
    By pwdError = By.xpath("//div[contains(text(),'Please enter password')]");
    By wrongpwd = By.xpath("//div[@class='error-message']");


    public CreateUserPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        js = new JavaScriptUtil(driver);
    }

    public void openUserPage() {
        String actMsg = driver.findElement(userPageTitle).getText();
        System.out.println(actMsg);
        String ExpectedTitle = "Users";
        Assert.assertEquals(actMsg, ExpectedTitle);

    }

    public void clickonAddnew() {
        vrutil.waitForElementVisible(newbtn, AppConstants.MEDIUM_TIME_OUT).click();
    }

    public void getUserPage()
    {
        vrutil.doClickanyMenu("Global Configuration", AppConstants.SHORT_TIME_OUT);
        vrutil.doClickanyMenu("General",  AppConstants.SHORT_TIME_OUT);
        vrutil.doClickanyMenu("Users",  AppConstants.SHORT_TIME_OUT);
    }

    public void creatUserWithMandatoryfields(String[] grps, String uname, String fName, String eID, String pwd, String cpwd) throws InterruptedException {

        WebElement selectUsergrp = vrutil.waitForElementVisible(Usergrp, AppConstants.MEDIUM_TIME_OUT);
        // String grps[] = {};

        for (String grp : grps) {
            //System.out.println(grp);
            selectUsergrp.click();
            WebElement option = driver
                    .findElement(By.xpath("//ul[@class='chosen-results']/child::li[text()='" + grp + "']"));
            option.click();
            String otext = option.getText();
            //System.out.println(otext);
        }
        vrutil.doSendKeys(userName, uname);
        vrutil.doSendKeys(firstName, fName);
        vrutil.doSendKeys(emailID, eID);
        vrutil.doSendKeys(password, pwd);
        vrutil.doSendKeys(CPassword, cpwd);
        vrutil.doClick(addUserbtn);

        WebElement msg = vrutil.waitForElementVisible(sucessMsg, AppConstants.MEDIUM_TIME_OUT);
        String ActualMsg = msg.getText();
        //System.out.println(ActualMsg);
        String ExpectedTitle = "The user has been added successfully";
        Assert.assertEquals(ActualMsg, ExpectedTitle);

    }

    public void ValidateProperNotificationMessagesAreDisplayedForMandatoryFields() {

        vrutil.waitForElementVisible(addUserbtn, AppConstants.MEDIUM_TIME_OUT).click();
        WebElement msg = vrutil.waitForElementVisible(userError, AppConstants.MEDIUM_TIME_OUT);

        String UserActualMsg = msg.getText();
        String ExpectedTitleforUser = "Please enter username";
        Assert.assertEquals(UserActualMsg, ExpectedTitleforUser);


        //Assert.assertEquals(ActualMsg, ExpectedTitle);
        String FirstActualMsg = driver.findElement(firstNError).getText();
        String FirstActMsg = msg.getText();
        String ExpectedTitleforFirst = "Please enter first name";
        Assert.assertEquals(FirstActualMsg, ExpectedTitleforFirst);

        String EmailActualMsg = driver.findElement(emailError).getText();
        String EmailActMsg = msg.getText();
        String ExpectedTitleforEmail = "Please enter email";
        Assert.assertEquals(EmailActualMsg, ExpectedTitleforEmail);

        String pwdActualMsg = driver.findElement(pwdError).getText();
        String pwdActMsg = msg.getText();
        String ExpectedTitleforpwd = "Please enter password";
        Assert.assertEquals(pwdActualMsg, ExpectedTitleforpwd);

    }

    public void ValidateRegisteringUserAccountProvidingAllFields(String[] grps, String uname, String fName, String lName, String number, String eID, String pwd, String cpwd) {

        WebElement selectUsergrp = vrutil.waitForElementVisible(Usergrp, AppConstants.MEDIUM_TIME_OUT);
        // String grps[] = {};

        for (String grp : grps) {
            //System.out.println(grp);

            selectUsergrp.click();
            WebElement option = driver
                    .findElement(By.xpath("//ul[@class='chosen-results']/child::li[text()='" + grp + "']"));
            option.click();
            String otext = option.getText();
            //System.out.println(otext);
        }
        vrutil.doSendKeys(userName, uname);
        vrutil.doSendKeys(firstName, fName);
        vrutil.doSendKeys(lastName, lName);
        vrutil.doSendKeys(cellPhone, number);
        vrutil.doSendKeys(emailID, eID);
        vrutil.doSendKeys(password, pwd);
        vrutil.doSendKeys(CPassword, cpwd);
        vrutil.doClick(addUserbtn);

        WebElement msg = vrutil.waitForElementVisible(sucessMsg, AppConstants.MEDIUM_TIME_OUT);
        String ActualMsg = msg.getText();
        //System.out.println(ActualMsg);
        String ExpectedTitle = "The user has been added successfully";
        Assert.assertEquals(ActualMsg, ExpectedTitle);
    }

    public void ValidateDifferentPasswordsPasswordConfirmPassword(String[] grps, String uname, String fName, String lName, String number, String eID, String pwd, String cpwd) {
        WebElement selectUsergrp = vrutil.waitForElementVisible(Usergrp, AppConstants.MEDIUM_TIME_OUT);
        // String grps[] = {};

        for (String grp : grps) {
            //System.out.println(grp);
            selectUsergrp.click();
            WebElement option = driver
                    .findElement(By.xpath("//ul[@class='chosen-results']/child::li[text()='" + grp + "']"));
            option.click();
            String otext = option.getText();
            //System.out.println(otext);
        }
        vrutil.doSendKeys(userName, uname);
        vrutil.doSendKeys(firstName, fName);
        vrutil.doSendKeys(lastName, lName);
        vrutil.doSendKeys(cellPhone, number);
        vrutil.doSendKeys(emailID, eID);
        vrutil.doSendKeys(password, pwd);
        vrutil.doSendKeys(CPassword, cpwd);
        vrutil.doClick(addUserbtn);

        WebElement msg = vrutil.waitForElementVisible(wrongpwd, AppConstants.MEDIUM_TIME_OUT);
        String pwdActualMsg = msg.getText();
        String ExpectedTitleforpwd = "Both password must match";
        Assert.assertEquals(pwdActualMsg, ExpectedTitleforpwd);
    }

    public void ValidateUserAccountProvidingExistingUserEmailID(String[] grps, String uname, String fName, String lName, String number, String eID, String pwd, String cpwd) {
        WebElement selectUsergrp = vrutil.waitForElementVisible(Usergrp, AppConstants.MEDIUM_TIME_OUT);
        // String grps[] = {};

        for (String grp : grps) {
            //System.out.println(grp);
            selectUsergrp.click();
            WebElement option = driver
                    .findElement(By.xpath("//ul[@class='chosen-results']/child::li[text()='" + grp + "']"));
            option.click();
            String otext = option.getText();
            //System.out.println(otext);
        }
        vrutil.doSendKeys(userName, uname);
        vrutil.doSendKeys(firstName, fName);
        vrutil.doSendKeys(lastName, lName);
        vrutil.doSendKeys(cellPhone, number);
        vrutil.doSendKeys(emailID, eID);
        vrutil.doSendKeys(password, pwd);
        vrutil.doSendKeys(CPassword, cpwd);
        vrutil.doClick(addUserbtn);

        WebElement msg = vrutil.waitForElementVisible(wrongpwd, AppConstants.MEDIUM_TIME_OUT);
        String pwdActualMsg = msg.getText();
        String ExpectedTitleforpwd = "This email already exist. Please Check in Users if Not Found In Owners.";
        Assert.assertEquals(pwdActualMsg, ExpectedTitleforpwd);
    }

    public void ValidateUserAccountProvidingInvalidEmailAddress(String[] grps, String uname, String fName, String lName, String number, String eID, String pwd, String cpwd) {
        WebElement selectUsergrp = vrutil.waitForElementVisible(Usergrp, AppConstants.MEDIUM_TIME_OUT);
        // String grps[] = {};

        for (String grp : grps) {
            //System.out.println(grp);
            selectUsergrp.click();
            WebElement option = driver
                    .findElement(By.xpath("//ul[@class='chosen-results']/child::li[text()='" + grp + "']"));
            option.click();
            String otext = option.getText();
            //System.out.println(otext);
        }
        vrutil.doSendKeys(userName, uname);
        vrutil.doSendKeys(firstName, fName);
        vrutil.doSendKeys(lastName, lName);
        vrutil.doSendKeys(cellPhone, number);
        vrutil.doSendKeys(emailID, eID);
        vrutil.doSendKeys(password, pwd);
        vrutil.doSendKeys(CPassword, cpwd);
        vrutil.doClick(addUserbtn);

        WebElement msg = vrutil.waitForElementVisible(wrongpwd, AppConstants.MEDIUM_TIME_OUT);
        String pwdActualMsg = msg.getText();
        String ExpectedTitleforpwd = "Please enter valid email";
        Assert.assertEquals(pwdActualMsg, ExpectedTitleforpwd);
    }

    public void ValidateLoggingApplicationEnableOptionForUserCredentials(String name) throws InterruptedException {
        vrutil.doClick(masterSearch);
        vrutil.doSendKeys(masterSearch, name);
        List<WebElement> allUser = driver.findElements(By.xpath("//ul[@id='ui-id-1']/child::li[@class='ui-menu-item']/descendant::div[contains(text(),'Sejal limbachiya')]"));
        for (WebElement e : allUser) {
            Thread.sleep(1000);
            String userNameList = e.getText();
            System.out.println(userNameList);
            if (userNameList.equals(name)) {
                e.click();
            }
        }
    }
}
