package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement eamilField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement warningMessageForEmailAndPass;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public void enterEmail(String emailText) {
		eamilField.sendKeys(emailText);
	}
	
    public void enterPassword(String passwordText) {
    	passwordField.sendKeys(passwordText);
	}
    
    public AccountPage clickOnLoginButton() {
    	loginButton.click();
    	return new AccountPage(driver);
	}
    
    public String retriveEmailPasswordNotMatchingWarning() {
    	String warningText = warningMessageForEmailAndPass.getText();
    	return warningText;
	}
	
}
