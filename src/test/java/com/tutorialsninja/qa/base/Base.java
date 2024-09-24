package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilitiles;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base() {
		
		prop = new Properties();
		File fileProp = new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa/config/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(fileProp);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dataProp = new Properties();
		File dataFile = new File(System.getProperty("user.dir")+"/src/main/java/com/tutirialsninja/qa/testdata/testdata.properties");
		
		
		FileInputStream dataFis;
		try {
			dataFis = new FileInputStream(dataFile);
			dataProp.load(dataFis);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
	}
	
	
	public WebDriver intilializeBrowserAndOpenApplicationURL(String browserName) {
		
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilitiles.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilitiles.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}

}
