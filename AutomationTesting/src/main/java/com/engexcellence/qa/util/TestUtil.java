package com.engexcellence.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.engexcellence.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	WebDriverWait wait;
	
	public static long PAGE_LOAD_TIMEOUT =20;
	public static long IMPLICIT_WAIT =10;
	public static long EXPLICIT_WAIT=30;
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/engexcellence/qa/testdata/Email_List_Test_data.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	public String osName;
	
	
	public static String getTwoFactorCode(){
		Totp totp = new Totp("qwertyuiopasdfghjklzxcvbnm");//2FA secret key
		String twoFactorcode = totp.now();//generate 2Factor code
		return twoFactorcode;
	}
	
	  /**
	   * This method is used for retrieving attribute value from a specific element.
	   * 
	   * @param element
	   * @param strAttribute
	   * @return
	   */
      public String getAttributeElement(WebElement element, String strAttribute){
    	  String strattributevalue = element.getAttribute(strAttribute);
    	  return strattributevalue;
	}
	
      
     /**
      * This method is used for waiting for an element until it is visible.
      * 
      * @param attributename
      */
	public void explicitWaitElementPresence(String attributename){
		 wait = new WebDriverWait(driver,EXPLICIT_WAIT);
		 By by = By.name(attributename);
		 //WebElement webelement = driver.findElement(by);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(by));	
	}
	
	/**
	 * This method is used for switching I-frame via passing index. 
	 * 
	 * @param index
	 */
	public void switchToIFrameIndex(int index){
		driver.switchTo().frame(index);
	}
	
	
	/**
	 * This method is used for mouse hover of a specific element in web-page.
	 * 
	 * @param element
	 */
	public void mousehoverElement(WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();	
	}
	
	/**
	 * This method is used for select drop-down value via passing index in web-page.
	 * 
	 * @param element
	 * @param index
	 */
	public void selectDropdownByIndex(WebElement element,int index){
		Select select =new Select (element);
		select.selectByIndex(index);;
	}
	
	/**
	 * This method is used for select drop-down value via passing value in web-page.
	 * 
	 * @param element
	 * @param value
	 */
	public void selectDropdownByValue(WebElement element,String value){
		Select select =new Select (element);
		select.selectByValue(value);
	}
	
	/**
	 * This method is used for select drop-down via passing visible text in web-page.
	 * 
	 * @param element
	 * @param visibletext
	 */
	public void selectDropdownByText(WebElement element, String visibletext){
		Select select = new Select(element);
		select.selectByVisibleText(visibletext);
	}
	
	public static Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		try{
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			book = WorkbookFactory.create(file);
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sheet= book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum();i++){
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				System.out.print(data[i][j]);
			}
		}
		
		return data;
		
	}
	
	/**
	 * This method is used for converting current timestamp in a specific format.
	 * @return
	 */
	public static String simpleDateFormat(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_MM_SS");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * This method is used to capture screen-shot in case of any exception has been occur.
	 * @throws IOException
	 */
	public static String captureScreenshot(WebDriver driver, String screenshotname) throws IOException{
		TakesScreenshot srcShot = ((TakesScreenshot) driver);
		
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir")+"/screenshots/"+TestUtil.simpleDateFormat()+".png";
		File DestFile=new File(destination);
		FileUtils.copyFile(srcFile,DestFile);
		return destination;
			//FileUtils.copyFile(srcFile, new File(currentDir+"/screenshots/"+TestUtil.simpleDateFormat()+".png"));
		
	}

}
