package com.qa.VR4.pages.GL;

import com.qa.VR4.utils.JavaScriptUtil;
import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OfficesMenuPage {

    private WebDriver driver;
    private VRUtils vrutil;
    private JavaScriptUtil js;


    //1. Private by locators - page locators
    //Default Office
    protected By officenametxt = By.name("name");
    protected By emailnametxt = By.name("email");
    protected By TaxDistrictsdrp = By.name("taxdistrict_id");
    protected By phonetxt = By.name("phone");

    //Mailing Address
    protected By address1txt = By.name("address1");
    protected By address2txt = By.name("address2");
    protected By citytxt = By.name("city");
    protected By statetxt = By.name("state");
    protected By PostalCodetxt = By.name("state");
    protected By countrytxt = By.name("country");
    protected By savebtn = By.xpath("//a[@class='btn btn-primary saveLocation']");
    protected By deletebtn = By.xpath("//a[@class='btn btn-secondary deleteLocation']");
    protected By backbtn = By.xpath("//i[@class='far fa-arrow-right']");


    public OfficesMenuPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);
        js = new JavaScriptUtil(driver);
    }

    public void addvalueinMandatoryField(String officeName,String TaxDistric)
    {
        vrutil.doSendKeys(officenametxt,officeName);
        vrutil.doSelectDropDownValue(TaxDistrictsdrp,TaxDistric);
        vrutil.doClick(savebtn);
    }



}
