package df.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import df.pages.BasePage; 
import df.myLogger.*;

/*
 * MainPanel page object. It extends PageBase class
 * It provides properties and methods to test MainPanel highlevel actions 
 */

public class MainPanel extends BasePage {
	
	/* 
	 * The Main Panel contains several HTML elements that will be represented as WebElements.
	 * The locators for these elements should only be defined once.
	 */
	
	// 'Dataset Definition' label
	By datasetDefinitionLocator = By.xpath(".//label/span[contains(text(),'Dataset Definition')]");
	// 'Settings' tab
	By settingsTabLocator = By.xpath(".//*[@class='panel-heading']//a[text()='Settings']");
	// Settings stub
	By settingsLoacator = By.id("tabSettings");
	// 'Events' tab
	By eventsTabLocator = By.xpath(".//*[@class='panel-heading']//a[text()='Events']");
	// Events tub
	By noEventsLocator = By.id("tabEvents");
	
	/* Constructor: initialized and load this page properties. */ 
	public MainPanel(WebDriver driver, Logging testLog){
		super(driver, testLog);
	}
	
	/* Verify if Definition Label is present */
	public boolean verifyDatasetDefinitionLabelPresence(){
		testLog.lg.info("Verifying of 'Dataset Definition' label presence");
		if (isElementPresent(datasetDefinitionLocator)){
			testLog.lg.info("'Dataset Definition' label is present");
			return true;
		} else {
			testLog.lg.info("'Dataset Definition' label isn't present");
			return false;
		} 
	}
		
	/* Click on Setting tab */
	public String clickSettingsTab () {
		testLog.lg.info("Click on 'Settings' tab");
		clickOnElement(settingsTabLocator);
		// get text from 'Settings' tab to verify that tab is open
		return getText(settingsLoacator);
	}
	
	/* Click on Events tab. Verification that tab is open */
	public String clickEventsTab () {
		testLog.lg.info("Click on 'Events' tab");
		clickOnElement(eventsTabLocator);
		// get text from 'Events' tab to verify that tab is open
		return getText(noEventsLocator);
	}
	
}
