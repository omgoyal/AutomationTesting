package com.engexcelllence.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.engexcellence.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method is used for entering email address value.
	 * 
	 * @param emailaddress
	 */
	public void enterEmailAddress(String emailaddress){
		txtEmailbox.clear();
		txtEmailbox.sendKeys(emailaddress);
	}
	
	/**
	 * This method is used for clicking next button.
	 * 
	 */
	public void clickNextButton(){
		btnNext.click();
	}
	
	/**
	 * This method is used for presence of password field.
	 * 
	 * @return
	 */
	public boolean isElementDisplayed(){
		boolean flag=false;
		if(txtPasswordbox.isDisplayed()){
			flag=true;
		}
		return flag;
		
	}
	
	
	/**
	 * This method is used for entering password value.
	 * @param password
	 */
	public void enterPassword(String password){
		txtPasswordbox.clear();
		txtPasswordbox.sendKeys(password);
	}
	
	
	
	
	
	//Webelement-OR
	@FindBy(xpath = "//input[@type='email']")
	@CacheLookup  /*store the element in the cache(create a small memory store the element in that every time when we call that elment it will fetch from that instead of html dom so that speed increase)*/
	WebElement txtEmailbox;
	
	@FindBy(xpath ="//span[text()='Next']")
	WebElement btnNext;
	
	@FindBy(xpath = "//input[@type='password']")
	public WebElement txtPasswordbox;

}
