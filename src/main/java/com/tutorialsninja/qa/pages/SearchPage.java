package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath = "//div[@id='content']/descendant::p[2]")
	private WebElement noProductmessage;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean displayValidSearchProduct() {
		boolean hpProduct= validHPProduct.isDisplayed();
		return hpProduct;
	}
	
	public String displayNoProductMessage() {
		String noProductText = noProductmessage.getText();
		return noProductText;
	}
	

}
