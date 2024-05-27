package com.qa.VR4.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class VRUtils {

    private WebDriver driver;
    private Actions act;
    private JavaScriptUtil js;

    public VRUtils(WebDriver driver) {

        this.driver = driver;

        js = new JavaScriptUtil(driver);
    }


    public void doclicksideMenu(String value) {
        driver.findElement(By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='" + value + "']")).click();
    }

    public void doSendKeys(By locator, String value) {
        getElement(locator).sendKeys(value);

    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public void clickAccountingMenuWhenReady(String value, int timeOut) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            WebElement accountMenu = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
                    "//div[@id='main_menu_tab_10']/child::ul[@class='nav contentmenu']/child::li/child::a/child::span[text()='"
                            + value + "']"))));
            accountMenu.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("getting element exception...Please check your locator again");
            e.printStackTrace();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            element = driver.findElement(locator);
        }
        return element;
    }

    public void clickMaintenanceMenuWhenReady(String value, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='" + value + "']")))).click();
    }
//	public void clickAccountingMenuWhenReady(String value, int timeOut) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='"+value+"']")))).click();
//	}

    public void clickElementWhendrpReady(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

    }

    public void executeJS(String js) {
//		String javascript = "document.querySelector('.invoiceamount').blur()";   
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(js);
    }

    public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickOnElement(By locator, String linkText) {
        List<WebElement> linksList = getElements(locator);
        System.out.println("total number of links = " + linksList.size());

        for (WebElement e : linksList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(linkText)) {
                e.click();
                break;
            }
        }
    }

    public void doSelectDropDownByVisibleText(By locator, String visibleText) {
        if (visibleText == null) {
            System.out.println("please pass the right visible text and it can not be null");
            return;
        }
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
    }


    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void selectReservationGrid(String unitName, int nights, String targetDate) throws InterruptedException {

        Actions act = new Actions(driver);
        int singleDayWidth = 24;
        //int nights = 3;
        WebElement recervationpoint = driver.findElement(By.xpath("//div[@class='reservationgridholderholder']"));// 427,220
//		String unitName = "AM118B"; // 220
        WebElement unit = driver.findElement(By.xpath("//table[@class='table table-bordered table-striped unitscode-holder']//td[contains(@unit_code,'" + unitName + "')]"));// 240,277
        WebElement quote = driver.findElement(By.xpath("//a[@id='quickquotemodelanchor']"));
        // Search unit
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(unitName);
        // this will find all matching nodes in calendar
        List<WebElement> allDates = driver.findElements(By.xpath(
                "//div[@class='day-header-holder']/table[@class='table']//tr[contains(@class,'daynumberheader')]//div"));// (453,
        // 201)

        // to do MAKE DATE COUNTER DYNAMIC
        int dateCounter = 0;
        // now we will iterate all values and will capture the text. We will select when
        // date is 28

        for (WebElement ele : allDates) {

            String date = ele.getText();
            dateCounter++;
            // System.out.println(date); // once date is 28 then click and break
            if (date.equals(targetDate)) {
                break;
            }
        }

        Actions act2 = new Actions(driver);

        // System.out.println(recervationpoint.getRect().x+" - "+
        // recervationpoint.getRect().y);
        act2
                // initially mouse will be at (0,0)
                .moveByOffset(recervationpoint.getRect().x + (singleDayWidth * dateCounter) + 2,
                        unit.getLocation().y + 2)
                // click and hold on current location, will be res div
                .click()

                // move by days + width
                .moveByOffset(singleDayWidth * nights, 3).contextClick().click(quote).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
        driver.findElement(By.xpath("//input[@id='searchguest']")).sendKeys("parekhsmruti31@gmail.com");
        Thread.sleep(3000);
        List<WebElement> guestlist = driver.findElements(By.xpath(
                "//ul//div[@class='d-flex justify-contnet-start align-items-start flex-wrap ui-menu-item-wrapper']"));
        for (WebElement guest : guestlist) {
            guest.click();
        }
        driver.findElement(By.xpath("//button[normalize-space()='Reserve']")).click();
//		String ActualMessage = driver.findElement(By.xpath("//div[@class='alert alert-success text-center p-3']"))
//				.getText();
//		Assert.assertEquals(ActualMessage, "Reservation Created Successfully.");
        driver.findElement(By.xpath("//button[@class='btn btn-secondary uk-modal-close closemodel']")).click();
//		driver.findElement(By.xpath(
//						"//div[@class='uk-modal-dialog uk-modal-body modal-content modal-dialog-scrollable']//div[@class='modal-header']//button[@type='button']"))
//				.click();


    }

    public String[] getMonthYear(String monthYearVal) {
        return monthYearVal.split(" ");
    }

    public void selectDate(String exDay, String exMonth, String exYear) {

        if (exMonth.equals("FEBRUARY") && Integer.parseInt(exDay) > 29) {
            System.out.println("Wrong date selected : " + exMonth + " : " + exDay + " ");
            return;
        }
//        if(Integer.parseInt(exDay)>31)
//        {
//            System.out.println("Wrong date selected : " + exMonth + " : "+exDay+" ");
//            return;
//        }
        String monthYearVal = driver.findElement(By.className("ui-datepicker-title")).getText();
        //System.out.println(monthYearVal);

        while (!(getMonthYear(monthYearVal)[0].equals(exMonth) && getMonthYear(monthYearVal)[1].equals(exYear))) {
            driver.findElement(By.xpath("//a[@title='Next']")).click();

            monthYearVal = driver.findElement(By.className("ui-datepicker-title")).getText();


        }
        try {
            driver.findElement(By.xpath("//a[text()='" + exDay + "']")).click();
        } catch (Exception e) {
            System.out.println("Wrong date selected : " + exMonth + " : " + exDay + " ");
        }

    }

    //----------------------SelectUnitName In Dispatch page ------------------

    public void selectUnitforDispatchPage(String inspectionUnitName) throws InterruptedException {

        Thread.sleep(5000);
        WebElement unitName = driver.findElement(By.xpath("//div[@eventtype='hk']/descendant::span[contains(text(),'" + inspectionUnitName + "')]"));
        unitName.click();

    }

    public void ValidateUnitWithActionButton(String dispatcUnitName, String actionTaskName) throws InterruptedException {
        Thread.sleep(5000);
        WebElement unitName = driver.findElement(By.xpath("//div[@eventtype='hk']/descendant::span[contains(text(),'" + dispatcUnitName + "')]"));
        unitName.click();
        String Actunitname = unitName.getText();
        //System.out.println(Actunitname);
        js.scrollIntoView(unitName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[contains(@class,'text-center')]/a[contains(text(),'" + actionTaskName + "')]")).click();
    }

    public void ValidateScrollUnitWithActionButton(String dispatcUnitName, String actionTaskName) throws InterruptedException {
        Thread.sleep(5000);
        WebElement unitName = driver.findElement(By.xpath("//div[@eventtype='hk']/descendant::span[contains(text(),'" + dispatcUnitName + "')]"));
        unitName.click();
        String Actunitname = unitName.getText();
        System.out.println("Actunitname" + Actunitname);

        Thread.sleep(5000);
        WebElement actionunitName = driver.findElement(By.xpath("//div[contains(@class,'text-center')]/a[contains(text(),'" + actionTaskName + "')]"));
        actionunitName.click();
        String expunitname = unitName.getText();
        System.out.println("expunitname" + expunitname);
        if (actionTaskName.equals(actionTaskName)) {
            driver.findElement(By.xpath("//a[@title='Today']")).click();
        }
        Thread.sleep(2000);
        js.scrollIntoView(unitName);

    }


    //Select dropdown
    public void doSelectDropDownValue(By locator, String dropDownValue) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();

        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(dropDownValue)) {
                e.click();
                break;
            }
        }
    }

    //Select dropdown with retuen type
    public String doSelectDropDownValuewithreturn(By locator, String dropDownValue) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        String selectedDRp = null;
        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(dropDownValue)) {
                selectedDRp= e.getText();
                break;
            }
        }
        return selectedDRp;
    }

    // Wait concept

    public WebElement waitForElementPresence(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitforMenuElementVisible(int timeout,String submenuName)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
        js.scrollIntoView(driver.findElement(By.xpath("//span[text()='" + submenuName + "']")));
        WebElement subMenu= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+submenuName+"']")));
        subMenu.click();


    }
}

