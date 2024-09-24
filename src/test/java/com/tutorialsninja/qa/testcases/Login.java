package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilitiles;

public class Login extends Base {
	
	public Login() {
		super();
	}
	
	LoginPage loginPage;
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver= intilializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage =new HomePage(driver);
		
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
		
		
//		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
//		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1, dataProvider = "supplyTestdata")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountPage.editYourAccountInformationOption(),"Edit your account information option is not displayed");	
		
//		driver.findElement(By.id("input-email")).sendKeys(email);
//		driver.findElement(By.id("input-password")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		WebElement confLoginText = driver.findElement(By.linkText("Edit your account information"));
		
	//	Assert.assertEquals(confLoginText,"Edit your account information");
//		Assert.assertTrue(confLoginText.isDisplayed());	
	}
	
	@DataProvider
	public Object[][] supplyTestdata() {
		
		Object [][] data = Utilitiles.getTestDataFromExcel("Login");
		return data;
	}
	
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.enterEmail(Utilitiles.generateEmailWithTimestamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningText = loginPage.retriveEmailPasswordNotMatchingWarning();
        Assert.assertTrue(actualWarningText.contains(dataProp.getProperty("warningMessageForEmailAndPass")),"Warning message regarding email or password is not displayed");
		
//		driver.findElement(By.id("input-email")).sendKeys(Utilitiles.generateEmailWithTimestamp());
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		WebElement warningText = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"));
//		
//		Assert.assertEquals(warningText.getText(),"Warning: No match for E-Mail Address and/or Password.");
//		Assert.assertTrue(warningText.isDisplayed());
		
		
		
		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailANdValidPasswordCredentials() {
		
		loginPage.enterEmail(Utilitiles.generateEmailWithTimestamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningText = loginPage.retriveEmailPasswordNotMatchingWarning();
        Assert.assertTrue(actualWarningText.contains(dataProp.getProperty("warningMessageForEmailAndPass")),"Warning message regarding email or password is not displayed");
		
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailANdInvalidPasswordCredentials() {
		
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningText = loginPage.retriveEmailPasswordNotMatchingWarning();
        Assert.assertTrue(actualWarningText.contains(dataProp.getProperty("warningMessageForEmailAndPass")),"Warning message regarding email or password is not displayed");
	
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvindingCredentials() {
		
        loginPage.clickOnLoginButton();
		
		String actualWarningText = loginPage.retriveEmailPasswordNotMatchingWarning();
        Assert.assertTrue(actualWarningText.contains(dataProp.getProperty("warningMessageForEmailAndPass")),"Warning message regarding email or password is not displayed");
	
		
//		driver.findElement(By.id("input-email")).sendKeys("");
//		driver.findElement(By.id("input-password")).sendKeys("");
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		WebElement warningText = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"));
//		
//		Assert.assertEquals(warningText.getText(),"Warning: No match for E-Mail Address and/or Password.");
//		Assert.assertTrue(warningText.isDisplayed());
//		
		
	}
	
	

}
