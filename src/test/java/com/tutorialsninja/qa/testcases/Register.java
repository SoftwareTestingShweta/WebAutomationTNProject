 package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilitiles;

public class Register extends Base {
	
	public Register() {
		super();
	}
	
	RegisterPage registerPage;
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = intilializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void  tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisterAnAccountWithMandetoryFields() {
		
		registerPage.enterFirstname(dataProp.getProperty("firstName"));
		registerPage.enterLastname(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilitiles.generateEmailWithTimestamp());
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectPrivacyPolicy();
        
		AccountSuccessPage accountSuccessPage = registerPage.clickOnContinueButton();
		
		String accountCreatedSuccessText = accountSuccessPage.getAccountSuccessText();
	//	Assert.assertEquals(accountCreatedSuccessText, dataProp.getProperty("accountCreatedText"));
			 
	}
	
	@Test(priority = 2)
	public void verifyRegisterAnAccountWithAllTheFields() {
		
		registerPage.enterFirstname(dataProp.getProperty("firstName"));
		registerPage.enterLastname(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilitiles.generateEmailWithTimestamp());
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectSubscriptionOption();
		registerPage.selectPrivacyPolicy();
		
		AccountSuccessPage accountSuccessPage = registerPage.clickOnContinueButton();
		
		String accountCreatedSuccessText = accountSuccessPage.getAccountSuccessText();
	//	Assert.assertEquals(accountCreatedSuccessText,dataProp.getProperty("accountCreatedText"));
		
	}
	
	@Test(priority = 3)
	public void verifyRegisterAnAccountWithExixtingEmailAddress() {
		
		registerPage.enterFirstname(dataProp.getProperty("firstName"));
		registerPage.enterLastname(dataProp.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));

		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		String actualWarning = registerPage.getDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("warningForAlreadyRegisteredEmail")),"Warning message regarding duplicate email address is not diaplayed");
			 
	}
	
	@Test(priority = 4)
	public void verifyRegisterAnAccountWithoutFillingAnyDetails() {
		
		registerPage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarning = registerPage.getPrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("actualPrivacyPolicyWarning")),"Warning message regarding privacy policy is not diaplayed");
		
		String actualFirstnameWarning = registerPage.getFirstNameWarning();
		Assert.assertTrue(actualFirstnameWarning.contains(dataProp.getProperty("actualFirstnameWarning")),"Warning message Firstname is not diaplayed");
		
		String actualLastnameWarning = registerPage.getLastNameWarning();
		Assert.assertTrue(actualLastnameWarning.contains(dataProp.getProperty("actualLastnameWarning")),"Warning message regarding lastname is not diaplayed");
		
		String actualEmailWarning = registerPage.getEmailAddressWarning();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("actualEmailWarning")),"Warning message regarding valid email is not diaplayed");
		
		String actualTelephoneWarning = registerPage.getTelephoneNumberWarning();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("actualTelephoneWarning")),"Warning message regarding telephone is not diaplayed");
		
		String actualPasswordWarning = registerPage.getPasswordWarning();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("actualPasswordWarning")),"Warning message regarding password is not diaplayed");
		
	}
	
	
	

}
