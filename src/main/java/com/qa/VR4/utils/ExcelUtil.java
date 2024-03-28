package com.qa.VR4.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtil {

    public static final String TEST_DATA_SHEET_PATH= "./src/test/resources/testdata/ADdata.xlsx";
    public static Workbook book;
    private static Sheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static Object[][] getTestData(String sheetName)
    {
        System.out.println("Reading the data from sheet : " +sheetName);
        Object data[][]=null;
        try {
            FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
            book= WorkbookFactory.create(ip);
            sheet=book.getSheet(sheetName);
            data =new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
            for(int i=0; i<sheet.getLastRowNum();i++)
            {
                for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
                {
                    data[i][j]=sheet.getRow(i+1).getCell(j).toString();
                }
            }
            return data;


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
    // New method to write data to Excel
    public static void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
    {
        File xlfile=new File(TEST_DATA_SHEET_PATH);
        if(!xlfile.exists())
        {
            book=new XSSFWorkbook();
            fo=new FileOutputStream(TEST_DATA_SHEET_PATH);
            book.write(fo);
        }

        fi=new FileInputStream(TEST_DATA_SHEET_PATH);
        book=new XSSFWorkbook(fi);

        if(book.getSheetIndex(sheetName)==-1)
            book.createSheet(sheetName);
        sheet=book.getSheet(sheetName);


        if(sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        row= (XSSFRow) sheet.getRow(rownum);



        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(TEST_DATA_SHEET_PATH);
        book.write(fo);

        fi.close();
        fo.close();

    }
}
