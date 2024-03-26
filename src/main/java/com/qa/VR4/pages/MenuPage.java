package com.qa.VR4.pages;

import com.qa.VR4.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    public MenuPage(WebDriver driver)
    {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    //1. private By Locators - page locators
    By reservationMenu = By.id("mainmenua9 ");
    By reservationGrid=By.xpath("//li[@id='menu_item_84']//a[contains(@class,'menu-item')]");

}
