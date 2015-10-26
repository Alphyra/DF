package df.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import df.pages.BasePage; 
import df.myLogger.*;

/*
 * DefinitionTab page object. It extends PageBase class
 * It provides properties and methods to test actions on at 'Definition' tab
 */

public class DefinitionTab extends BasePage {
	
	/* 
	 * The Definition Tab contains several HTML elements that will be represented as WebElements.
	 * The locators for these elements should only be defined once.
	 */
	
	// 'Definition' tab
	By definitionTabLocator = By.xpath(".//*[@class='panel-heading']//a[text()='Definition']");
	// Info label
	By infoLabel = By.xpath(".//*[@id='tabDefinition']/div[3]");
	// 'Start Preview' button
	By startPreviewBtnLocator = By.xpath(".//*[@id='tabDefinition']//button[text()='Start Preview']");
	// 'Clear Preview Data' button
	By clearPreviewDataBtnLocator = By.xpath(".//*[@id='tabDefinition']//button[text()='Clear Preview Data']");
	// 'Clear All' button
	By clearAllBtnLocator = By.xpath(".//*[@id='tabDefinition']//button[text()='Clear All']");
	// 'Stop Preview' button
	By stopPreviewBtnLocator = By.xpath(".//*[@id='tabDefinition']//button[text()='Stop Preview']");
	// Any attribute at 'Definition' tab
	By anyAttribute = By.xpath(".//*[@class='pointer']/span");
	
	/* Constructor: initialized and load this page properties. */
	public DefinitionTab(WebDriver driver, Logging testLog){
		super(driver, testLog);
	}
	
	/* Verify if Definition Tab is present */
	public boolean verifyDefinitionTabPresence() {
		testLog.lg.info("Verifying of Definition Tab presence");
		if (isElementPresent(definitionTabLocator)){
			testLog.lg.info("Definition Tab is present");
			return true;
		} else {
			testLog.lg.info("Definition Tab isn't present");
			return false;
		} 		
	}
	
	/* Verify if 'Start Preview' button is disabled */
	public boolean verifyStartPreviewBtnIsDisabled() {
		testLog.lg.info("Verify if 'Start Preview' button is disabled");
		String attributeValue = getAttributeValue(startPreviewBtnLocator, "class");
		if (attributeValue.contains("disabled")) {
			testLog.lg.info("'Start Preview' button is disabled");
			return true;
		} else {
			testLog.lg.info("'Start Preview' button is enable");
			return false;
		}
	}
	
	/* Verify if 'Clear Preview Data' button is disabled */
	public boolean verifyClearPreviewDataBtnIsDisabled() {
		testLog.lg.info("Verify if 'Clear Preview Data' button is disabled");
		String attributeValue = getAttributeValue(clearPreviewDataBtnLocator, "class");
		if (attributeValue.contains("disabled")) {
			testLog.lg.info("'Clear Preview Data' button is disabled");
			return true;
		} else {
			testLog.lg.info("'Clear Preview Data' button is enable");
			return false;
		}
	}
	
	/* Verify if 'Clear All' button is disabled */
	public boolean verifyClearAllBtnIsDisabled() {
		testLog.lg.info("Verify if 'Clear All' button is disabled");
		String attributeValue = getAttributeValue(clearAllBtnLocator, "class");
		if (attributeValue.contains("disabled")) {
			testLog.lg.info("'Clear All' button is disabled");
			return true;
		} else {
			testLog.lg.info("'Clear All' button is enable");
			return false;
		}
	}
		
	/* Click on 'Start Preview' button */
	public void clickStartPreviewButton() {
		testLog.lg.info("Click 'Start Preview' button");
		clickOnElement(startPreviewBtnLocator);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			testLog.lg.info(e.getMessage());			
		}
		
	}
	
	/* Click on 'Clear Preview Data' button */
	public void clickClearPreviewDataButton() {
		testLog.lg.info("Click 'Clear Preview Data' button");
		clickOnElement(clearPreviewDataBtnLocator);
	}
	
	/* Click on 'Clear All' button */
	public void clickClearAllButton() {
		testLog.lg.info("Click 'Clear All' button");
		clickOnElement(clearAllBtnLocator);
	}
	
	/* Click on 'Stop Preview' button */
	public void clickStopPreview() {
		testLog.lg.info("Click 'Stop Preview' button");
		clickOnElement(stopPreviewBtnLocator);
		WebDriverWait waitForStart = new WebDriverWait(driver, 10000);
		waitForStart.until(ExpectedConditions.presenceOfElementLocated(startPreviewBtnLocator));
	}
	
	/* Verify text at Info label under button group */
	public boolean verifyTextAtInfoLabel(String expText) {
		testLog.lg.info("Verify text at info label. Expected text: " + expText);
		String actText;
		try {
			actText = getText(infoLabel);
			if (actText.contains(expText)){
				testLog.lg.info("text '" + expText + "' is present at Info Label");
				return true;
			} else {
				testLog.lg.info("text '" + expText + "' ISN'T present at Info Label. Unexpected text value: " + actText);
				return false;
			}
		} catch (Exception e) {
			testLog.lg.debug("ERORR during search text '" + expText + "' at Info Label\n" + e.getMessage());
			return false;
		}		
	}
	
	/* Verify if 'Dataset Definition' tab is empty */
	public boolean verifyDefinitionTabClear() {
		testLog.lg.info("Verify if 'Dataset Definition' tab is clear");
		if (isElementPresent(anyAttribute)){
			testLog.lg.info("'Dataset Definition' tab ISN'T clear");
			return false;
		} else {
			testLog.lg.info("'Dataset Definition' tab is clear");
			return true;
		}
	}
	
	/* return Name of attribute which is shown at 'Definition' tab after selection at Left Navigation bar */
	public String getAttributeName() {
		testLog.lg.info("Get name of selected attribute at 'Definition' tab");
		String attributeName = getText(anyAttribute);
		if (attributeName != null && !attributeName.isEmpty()) {
			testLog.lg.info("Definition Tab contains attribute '" + attributeName + "'");
			return attributeName;
		} else {
			return null;
		}		
	}
	
	
}
