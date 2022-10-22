package com.engexcelllence.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.engexcellence.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath ="//header[@role='banner']/descendant::a[2]")
	WebElement eleTitle;

}
