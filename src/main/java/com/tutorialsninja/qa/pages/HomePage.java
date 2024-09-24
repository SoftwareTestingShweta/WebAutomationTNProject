package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath = "//span[text()=\"My Account\"]")
	private WebElement myAccountDropdownMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption; 
	
	@FindBy(xpath  = "//input[@name='search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath  = "//span[@class='input-group-btn']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	
	public void clickOnMyAccount() {
		myAccountDropdownMenu.click();;
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterInSearchBoxField(String enterProduct) {
		searchBoxField.sendKeys(enterProduct);
	}
	
	public SearchPage clickOnSearch() {
		searchButton.click();
		return new SearchPage(driver);
	}

}
