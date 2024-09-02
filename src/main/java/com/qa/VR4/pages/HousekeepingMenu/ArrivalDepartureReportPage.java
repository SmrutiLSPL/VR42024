package com.qa.VR4.pages.HousekeepingMenu;

import com.qa.VR4.utils.ExcelUtil;
import com.qa.VR4.utils.VRUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ArrivalDepartureReportPage {
    private WebDriver driver;
    private VRUtils vrutil;
    private JavascriptExecutor executor;
    private ExcelUtil xutils;


    ArrayList<String> adData = new ArrayList<String>();
    ArrayList<String> aData = new ArrayList<String>();
    ArrayList<String> dData = new ArrayList<String>();
    ArrayList<String> inspData = new ArrayList<String>();
    ArrayList<String> hkData = new ArrayList<String>();



    By housekeepingMenu = By.xpath("//a[@id='mainmenua7 ']");
    By openStartDate = By.xpath("//input[@name='forhkrepstartdate']");
    By clickCalendarIcon = By.xpath("//a[@class='datechanger btn btn-default ']");
    By ClickarrivalCalendarIcon=By.xpath("//a[@class='datechanger btn btn-sm btn-default ']");
    By openendDate = By.xpath("//input[@name='forhkrependdate']");
    By generatebtn = By.xpath("//input[@name='which']");

    public ArrivalDepartureReportPage(WebDriver driver) {
        this.driver = driver;
        vrutil = new VRUtils(driver);

        executor = (JavascriptExecutor) this.driver;
    }

    public void clickmainmenu() throws InterruptedException {
        vrutil.doClick(housekeepingMenu);
    }

    public void clickOnListOfMenu() {
        vrutil.clickMaintenanceMenuWhenReady("Reports", 5);
    }

    public void clickOnsubListOfMenu(String menuName) throws InterruptedException, IOException {
        vrutil.clickMaintenanceMenuWhenReady(menuName, 5);
    }

    public void openStartDatePicker() {
        vrutil.doClick(openStartDate);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
    }

    public void openEndDatePicker() {
        vrutil.doClick(openendDate);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
    }

    public void opendispatchDatePicker() {
        vrutil.doClick(clickCalendarIcon);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='datechanger btn btn-default ']")));
    }

    public void openArrivalDatePicker() {
        vrutil.doClick(ClickarrivalCalendarIcon);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
    }


    public void clickgenerate() throws InterruptedException {
        vrutil.doClick(generatebtn);
    }

    public void printADdata() throws IOException {
        int col = 0;

        List<WebElement> Title = driver.findElements(By.xpath("//div[@id='thisneedstobeprinted']//h5[@class='grayback mt-3 mb-3 gray-sub-head']"));




        for (WebElement adtitle : Title) {
            String arrival = adtitle.getText();

            xutils.setCellData("sheet1", 0, col, arrival);
            //List<WebElement> table = driver.findElements(By.xpath("(//div[contains(@class,'table-responsive mb-3')]["+(col + 1)+"]/table[@class='table table-striped']//tbody//td[position()=1]/child::b"));
            List<WebElement> table = driver.findElements(By.xpath("(//div[contains(@class,'table-responsive mb-3')])[" + (col + 1) + "]/table[@class='table table-striped']//tbody//td[1]/child::b[text()!='Flags : ']"));

            for (int i = 0; i < table.size(); i++) {
                if (arrival.equals("Arrivals")) {
                    adData.add(table.get(i).getText());

                } else if (arrival.equals("Departures")) {
                    dData.add(table.get(i).getText());

                } else {
                    System.out.println("Condition fail");
                }
                aData.add(table.get(i).getText());
                xutils.setCellData("sheet1", i + 1, col, table.get(i).getText());
            }
            col++;
        }
        System.out.println("Total Array list Size of Arrival 0f AD form data : " + adData.size());
        System.out.println("Total Array list Size of depature  0f AD form data : " + dData.size());

    }

//    public void openmainMenu()
//    {
//        vrutil.clickMaintenanceMenuWhenReady("Dispatch", 5);
//    }
    public void validateInspectionHousekeepingData() throws IOException {
        // Navigate on dispatch menu and select Assignment Date
        // select date
        try {
            Thread.sleep(2000);

            xutils.setCellData("sheet1", 0, 2, "Inspection");

            xutils.setCellData("sheet1", 0, 3, "Houskeeping");

            int col1 = 2;
            int col2 = 3;
            List<WebElement> inspElems = driver.findElements(
                    By.xpath("//div[contains(@class,'eventbody')][@eventtype='insp']//span[@class='unit_code_label']"));
            List<WebElement> hkElems = driver.findElements(By.xpath(
                    "//div[contains(@eventtype,'hk')] [@title!='Laundry Pickup'][@title!='Laundry Drop-off']//span[@class='unit_code_label']"));
            // System.out.println(inspElems.size());
            // System.out.println(hkElems.size());


            for (int i = 0; i < inspElems.size(); i++) {
                inspData.add(inspElems.get(i).getText());
                xutils.setCellData("sheet1", i + 1, col1, inspElems.get(i).getText());

            }

            for (int i = 0; i < hkElems.size(); i++) {

                hkData.add(hkElems.get(i).getText());
                xutils.setCellData("sheet1", i + 1, col2, hkElems.get(i).getText());

            }
            System.out.println("Total Array list Size of Inspection data : " + inspData.size());
            System.out.println("Total Array list Size of Housekeeping data : " + hkData.size());


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void comparedatawithDispatch() throws IOException {
        {
            xutils.setCellData("sheet1", 0, 5, "Departure => Housekeeping");
            for (int i = 0; i < hkData.size(); i++) {
                if (dData.contains(hkData.get(i))) {
                    xutils.setCellData("sheet1", i + 1, 5, "Value Match");
                } else {
                    xutils.setCellData("sheet1", i + 1, 5, "Value Does not Match");
                }
            }
            xutils.setCellData("sheet1", 0, 6, "Arrival  => Inspection");
            for (int i = 0; i < inspData.size(); i++) {
                if (aData.contains(inspData.get(i))) {
                    xutils.setCellData("sheet1", i + 1, 6, "Value Match");
                } else {
                    xutils.setCellData("sheet1", i + 1, 6, "Value Does not Match");
                }
            }
        }
    }
    public void compareDataWithArrivalPage() throws IOException {
        // Navigate on arrival menu and select Assignment Date
        int col3 = 4;
        xutils.setCellData("sheet1", 0, 4, "ArrivalData");

        List<WebElement> arrivalElems = driver
                .findElements(By.xpath("//table[@id='arrivalunits']/child::tbody//td[1]"));
        ArrayList<String> arrivalData = new ArrayList<String>();

        // System.out.println(arrivalElems.size());

        for (int i = 0; i < arrivalElems.size(); i++) {
            arrivalData.add(arrivalElems.get(i).getText());
            xutils.setCellData("sheet1", i + 1, col3, arrivalElems.get(i).getText().replace("Cancelled", ""));
        }

        System.out.println("Array size of Arrival data " + arrivalData.size());

        // 6 col ad => a

//		aData
//		adData

        xutils.setCellData("sheet1", 0, 7, "FORMULA A => AD");
        for (int i = 0; i < arrivalData.size(); i++) {
            if (adData.contains(arrivalData.get(i))) {
                xutils.setCellData("sheet1", i + 1, 7, "Value Match");
            } else {
                xutils.setCellData("sheet1", i + 1, 7, "Value Does not Match");
            }
        }
        int count = 0;

        xutils.setCellData("sheet1", 0, 8, "Arrival=>Inspection=>Arrival&Depature");
        for (int i = 0; i < adData.size(); i++) {
            String singleAD = adData.get(i);
            if(inspData.contains(singleAD) && arrivalData.contains(singleAD)) {
                xutils.setCellData("sheet1", i + 1, 8, "Value Match");
            } else {
                xutils.setCellData("sheet1", i + 1, 8, "Value Does not Match");
            }
    }

}}





//
////		aData
////		adData

//
//        // Navigate on arrival menu and select Assignment Date
//        int col3 = 4;
//        xutils.setCellData("sheet1", 0, 4, "ArrivalData");
//
//        List<WebElement> arrivalElems = driver
//                .findElements(By.xpath("//table[@id='arrivalunits']/child::tbody//td[1]"));
//        ArrayList<String> arrivalData = new ArrayList<String>();
//
//        // System.out.println(arrivalElems.size());
//
//        for (int i = 0; i < arrivalElems.size(); i++) {
//            arrivalData.add(arrivalElems.get(i).getText());
//            xutils.setCellData("sheet1", i + 1, col3, arrivalElems.get(i).getText().replace("Cancelled", ""));
//        }
//
//        System.out.println("Array size of Arrival data " + arrivalData.size());
//
//        // 6 col ad => a
//
////		aData
////		adData
//
//        xutils.setCellData("sheet1", 0, 7, "FORMULA A => AD");
//        for (int i = 0; i < arrivalData.size(); i++) {
//            if (adData.contains(arrivalData.get(i))) {
//                xutils.setCellData("sheet1", i + 1, 7, "Value Match");
//            } else {
//                xutils.setCellData("sheet1", i + 1, 7, "Value Does not Match");
//            }
//        }
//        int count = 0;
//
//        xutils.setCellData("sheet1", 0, 8, "Arrival=>Inspection=>Arrival&Depature");
//        for (int i = 0; i < adData.size(); i++) {
//            String singleAD = adData.get(i);
//            if(inspData.contains(singleAD) && arrivalData.contains(singleAD)) {
//                xutils.setCellData("sheet1", i + 1, 8, "Value Match");
//            } else {
//                xutils.setCellData("sheet1", i + 1, 8, "Value Does not Match");
//            }

        //=============================

//			for (int j = 0; j < inspData.size(); j++) {
//
//				for (int k = 0; k < arrivalData.size(); k++) {
//					if ( adData.contains(inspData.get(j)) == (adData.contains(arrivalData.get(k)))) {
//						count = count + 1;
//						if (adData.contains(arrivalData.get(i))) {
//							xutils.setCellData("sheet1", i + 1, 8, "Value Match");
//						} else {
//							xutils.setCellData("sheet1", i + 1, 8, "Value Does not Match");
//						}
//					}
//
//				}
//			}


        // xutils.setCellData("sheet1", 0, 7, "A => AD");
//		int maxRow = Math.max(aData.size(), adData.size());
//		for (int i = 0; i < maxRow; i++) {
//			xutils.setCellFormula("sheet1", i + 1, 7,
//					"IF(ISERROR(VLOOKUP(E" + (i + 2) + ",$A$2:$A$" + (maxRow + 1) + ",1,Value Does not Match)),Value Does not Match,Value Match)");
//		}

        // xutils.workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();






