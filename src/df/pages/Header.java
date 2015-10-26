package df.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import df.pages.BasePage; 
import df.myLogger.*;

/*
 * Header page object. It extends PageBase class
 * It provides properties and methods to test Header 
 */


public class Header extends BasePage {
	
	/* 
	 * The Header contains several HTML elements that will be represented as WebElements.
	 * The locators for these elements should only be defined once.
	 */
   	
	// Edifecs Logo
	By logoLocator = By.xpath("//img[@src='images/logo.png']");
	// 'Dashboard' link
	By dashboardLocator = By.linkText("Dashboard");
	// 'Settings' link
	By settingsLocator = By.linkText("Settings");
	// 'Profile' link
	By profileLocator = By.linkText("Profile");
	// 'Help' link
	By helpLocator = By.linkText("Help");
	
	/* Constructor: initialized and load this page properties. */ 
	public Header(WebDriver driver, Logging testLog){
		super(driver, testLog);
	}
	
	/* Verify if Edifecs logo is present */
	public boolean verifyLogoPresence(){
		testLog.lg.info("Verifying of logo presence");
		if (isElementPresent(logoLocator)){
			testLog.lg.info("Logo is present");
			return true;
		} else {
			testLog.lg.info("Logo isn't present");
			return false;
		} 
	}
	
	/* Verify if Dashboard link is present */
	public boolean verifyDashboardLinkPresence(){
		testLog.lg.info("Verifying of Dashboard link presence");
		if (isElementPresent(dashboardLocator)){
			testLog.lg.info("Dashboard link is present");
			return true;
		} else {
			testLog.lg.info("Dashboard link isn't present");
			return false;
		}
	}
	
	/* Verify is Settings link is present */
	public boolean verifySettingsLinkPresence(){
		testLog.lg.info("Verifying of Settings link presence");
		if (isElementPresent(settingsLocator)) {
			testLog.lg.info("Settings link is present");
			return true;
		} else {
			testLog.lg.info("Settings link isn't present");
			return false;
		}		
	}
	
	/* Verify if Profile link is present */
	public boolean verifyProfileLinkPresence(){
		testLog.lg.info("Verifying of Profile link presence");
		if (isElementPresent(profileLocator)) {
			testLog.lg.info("Profile link is present");
			return true;
		} else {
			testLog.lg.info("Profile link isn't present");
			return false;
		}		
	}
	 
	/* Verify if Help link is present */
	public boolean verifyHelpLinkPresence(){
		testLog.lg.info("Verifying of Help link presence");
		if (isElementPresent(helpLocator)) {
			testLog.lg.info("Help link is present");
			return true;
		} else {
			testLog.lg.info("Help link isn't present");
			return false;
		}		
	}

}
