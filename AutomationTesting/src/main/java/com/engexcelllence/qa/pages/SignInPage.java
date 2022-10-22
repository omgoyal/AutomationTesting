package com.engexcelllence.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.engexcellence.qa.base.TestBase;

public class SignInPage extends TestBase {
	
	
	public SignInPage(){
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String getSignInPageTitle(){
		return driver.getTitle();
	}
	
	
	public void clickSignInButton(){
		SignInButton.click();
	}
	
	//PageFactory -OR 
	@FindBy(xpath="//a[text()='Sign in']")
	WebElement SignInButton;

	
}
