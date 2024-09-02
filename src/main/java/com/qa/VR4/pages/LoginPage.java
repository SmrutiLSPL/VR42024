package com.qa.VR4.pages;

import com.qa.VR4.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

        private WebDriver driver;
        private ElementUtil eleUtil;

        //1. private By Locators - page locators
        By emailAddress = By.name("Users[email]");
        By NextBtn = By.xpath("//a[text()=' Next ']");
        By password = By.id("users-password");
        By selectdrp = By.name("userlayout");
        By submitbtn = By.id("loginSubmitBtn");
        By forgotlink = By.linkText("Forgot Password?");
        By ownsignlink = By.linkText("Owner Sign In");
        By ErrorMsg=By.xpath("//div[@class='toast-message']");
        By profile=By.xpath("//a[@id='profileDropdown']");
        By logout=By.xpath("//a[normalize-space()='Log Out']");


        //2. public page Constructor

        public LoginPage(WebDriver driver)
        {
            this.driver=driver;
            eleUtil = new ElementUtil(driver);
        }

        //3.  public page actoin/Methos

        public String getLoginPageTitle()
        {
            String actTitle=driver.getTitle();
            System.out.println(actTitle);
            return actTitle;
        }
        public String getLoginPageURL()
        {
            String actURL=driver.getCurrentUrl();
            System.out.println(actURL);
            return actURL;
        }

        public boolean IsForgotpwdLinkExist()
        {
            return driver.findElement(forgotlink).isDisplayed();
        }

        public boolean IsOwnSignlinkExist()
        {
            return driver.findElement(ownsignlink).isDisplayed();
        }
        public String VRLogin(String username, String pwd,String visibleText) throws InterruptedException {
           // System.out.println("App creds are : "+username+":"+pwd);

            Thread.sleep(1000);
            WebElement usernameField = driver.findElement(emailAddress);
            if (!usernameField.getAttribute("value").isEmpty()) {
                usernameField.clear();
            }

            eleUtil.doSendKeys(emailAddress, username);

            eleUtil.doClick(NextBtn);
            Thread.sleep(1000);
            WebElement passwordField = driver.findElement(password);
            if (!passwordField.getAttribute("value").isEmpty()) {
                passwordField.clear();
            }
            eleUtil.doSendKeys(password, pwd);
            eleUtil.doSelectDropDownByVisibleText(selectdrp,visibleText);
            eleUtil.doClick(submitbtn);
            String acttitle=driver.getTitle();

            Thread.sleep(1000);
            eleUtil.doClick(profile);
            eleUtil.doClick(logout);


            return acttitle;
        }
        public String VRloginWithAllInvaliddata(String username, String pwd, String visibleText) throws InterruptedException {
           // System.out.println("App creds are : "+username+":"+pwd);
            Thread.sleep(1000);
            WebElement usernameField = driver.findElement(emailAddress);
            if (!usernameField.getAttribute("value").isEmpty()) {
                usernameField.clear();
            }

            eleUtil.doSendKeys(emailAddress, username);

            eleUtil.doClick(NextBtn);
            Thread.sleep(1000);
            WebElement passwordField = driver.findElement(password);
            if (!passwordField.getAttribute("value").isEmpty()) {
                passwordField.clear();
            }
            eleUtil.doSendKeys(password, pwd);
            eleUtil.doSelectDropDownByVisibleText(selectdrp,visibleText);
            eleUtil.doClick(submitbtn);
            String actErrorMsg=driver.findElement(ErrorMsg).getText();
            //System.out.println(actErrorMsg);
            //return actErrorMsg;


            return actErrorMsg;
        }





}
