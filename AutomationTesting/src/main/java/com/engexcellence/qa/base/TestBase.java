package com.engexcellence.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.engexcellence.qa.util.TestUtil;
import com.engexcellence.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListner;
	
	public TestBase(){
		
		try{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/engexcellence/qa/config/config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}			
	}
	
	public static void initialization(){
		
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/driver/firefoxdriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		//Create a object of EventListnerHandler to register it with EventfiringWebdriver
		eventListner = new WebEventListener();
		e_driver.register(eventListner);
		driver =e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	} 

}