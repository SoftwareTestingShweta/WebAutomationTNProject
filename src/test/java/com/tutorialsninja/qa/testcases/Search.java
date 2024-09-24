package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base{
	
	public Search() {
		super();
	}
	
	SearchPage searchPage;
	WebDriver driver;
	
	@BeforeMethod
    public void setUp() {
		driver = intilializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		
		homePage.enterInSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearch();
	    
		Assert.assertTrue(searchPage.displayValidSearchProduct(),"Valid product is not displayed");
		
	}
	
	@Test(priority = 2)
    public void verifySearchWithInvalidProduct() {
		
        HomePage homePage = new HomePage(driver);
		
		homePage.enterInSearchBoxField(dataProp.getProperty("invaidProduct"));
		searchPage = homePage.clickOnSearch();
		
	    Assert.assertEquals(searchPage.displayNoProductMessage(),"There is no product that matches the search criteria.");
		
	}
	
	@Test(priority = 3)
    public void verifySearchWithoutAnyProduct() {
		
        HomePage homePage = new HomePage(driver);
        searchPage = homePage.clickOnSearch();
		
	    Assert.assertEquals(searchPage.displayNoProductMessage(),"There is no product that matches the search criteria.");
	}

}
