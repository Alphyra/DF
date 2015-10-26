package df.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import df.pages.BasePage; 
import df.myLogger.*;

import java.util.List;
import java.util.Random;

/*
 * LeftSideBar page object. It extends PageBase class
 * It provides properties and methods to test LeftSideBar actions 
 */

public class LeftNavBar extends BasePage {
	
	/* 
	 * The Left Side bar contains several HTML elements that will be represented as WebElements.
	 * The locators for these elements should only be defined once.
	 */
	
	// 'Select Template' dropdown
	By selectTemplateMenuLocator = By.xpath(".//*[@id='app']//a[@class='dropdown-toggle']");
	// 'Claim' menu item
	By claimMenuItemLocator = By.xpath(".//*[@class='dropdown-menu']//a[text()='Claim']");
    // 'Member' menu item 
	By memberMenuItemLocator = By.xpath(".//*[@class='dropdown-menu']//a[text()='Member']");
	// 'Provider' menu item
	By providerMenuItemLocator = By.xpath(".//*[@class='dropdown-menu']//a[text()='Provider']");
	// Claim Group
	By claimGroupLocator = By.xpath("(.//li[contains(@data-reactid,'Claim')])[2]");
	// Member Group
	By memberGroupLocator = By.xpath("(.//li[contains(@data-reactid,'Member')])[2]");
	// Provider Group
	By providerGroupLocator = By.xpath("(.//li[contains(@data-reactid,'Provider')])[2]");
	// 'Claim' group header
	By claimGroupHeaderLocator = By.xpath("(.//li[contains(@data-reactid,'Claim')])[2]/a[1]");
	// 'Member' group in 'Claim' template header
	By memberInClaimGroupHeader = By.xpath(".//li[contains(@data-reactid,'member')]/a[1]");
	// 'Provider' group in 'Claim' template header
	By providerInClaimGroupHeader = By.xpath(".//li[contains(@data-reactid,'billingProvider')]/a[1]");
	// 'Member' group header
	By memberGroupHeaderLocator = By.xpath("(.//li[contains(@data-reactid,'Member')])[2]/a[1]");
	// 'Provider' group header
	By providerGroupHeaderLocator = By.xpath("(.//li[contains(@data-reactid,'Provider')])[2]/a[1]");
	// 'Claim' group wrapper
	By claimWrapperLoactor = By.xpath("(.//li[contains(@data-reactid,'Claim')])[2]/a[1]/span[1]");
	// 'Member' group wrapper
	By memberWrapperLocator = By.xpath("(.//li[contains(@data-reactid,'Member')])[2]/a[1]/span[1]");
	// 'Provider' group wrapper
	By providerWrapperLocator = By.xpath("(.//li[contains(@data-reactid,'Provider')])[2]/a[1]/span[1]");
	// any element at Claim group only
	By claimAttributeLocator = By.xpath("(.//li[contains(@data-reactid,'Claim')])[2]/a");
	// any element at Member group
	By memberAttributeLocator = By.xpath("(.//li[contains(@data-reactid,'Member')])[2]/a");
	// any element at Provider group
	By providerAttributeLocator = By.xpath("(.//li[contains(@data-reactid,'Provider')])[2]/a");
	// any attribute at Claim+Member+Provider groups, which is shown when 'Claim' selected
	By attributeLocator = By.xpath(".//*[contains(@class,'list-group-item pointer')]/span[1]");
	// Search field
	By searchLocator = By.xpath(".//input[@type='search']");
	// XPath for abstract attribute - result of search
	// searchValue - will be replaced by real search value
	String searchResultXPath = ".//span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'searchValue')]";
				
	/* Constructor: initialized and load this page properties. */ 
	public LeftNavBar(WebDriver driver, Logging testLog) {
		super(driver, testLog);
	}
	
	/* Click on template menu locator to open menu */
	public void openTemplateMenu() {
		testLog.lg.info("Open template menu");
		clickOnElement(selectTemplateMenuLocator);
	}
	
	/* Click on 'Claim' menu item at template menu */
	public void selectClaimMenuItem() {
		testLog.lg.info("Select 'Claim' menu item");
		openTemplateMenu();
		clickOnElement(claimMenuItemLocator);
		WebDriverWait waitForClaimGroup = new WebDriverWait(driver, 10000);
		waitForClaimGroup.until(ExpectedConditions.presenceOfElementLocated(claimGroupHeaderLocator));
	}
	
	/* Click on 'Member' menu item at template menu */
	public void selectMemberMenuItem() {
		testLog.lg.info("Select 'Member' menu item");
		openTemplateMenu();
		clickOnElement(memberMenuItemLocator);
		WebDriverWait waitForMemberGroup = new WebDriverWait(driver, 10000);
		waitForMemberGroup.until(ExpectedConditions.presenceOfElementLocated(memberGroupHeaderLocator));
	}
	
	/* Click on 'Provider' menu item at template menu */
	public void selectProviderMenuItem() {
		testLog.lg.info("Select 'Provider' menu item");
		openTemplateMenu();
		clickOnElement(providerMenuItemLocator);
		WebDriverWait waitForProviderGroup = new WebDriverWait(driver, 10000);
		waitForProviderGroup.until(ExpectedConditions.presenceOfElementLocated(providerGroupHeaderLocator));
	}
	
	/* Click on 'Claim' Group header */
	public void clickOnClaimGroupHeader() {
		testLog.lg.info("Click on 'Claim' group header");
		clickOnElement(claimGroupHeaderLocator);
	}
	
	/* Click on 'Member' Group header in Claim template */
	public void clickOnMemberInClaimGroupHeader() {
		testLog.lg.info("Click on 'Member' group header in Claim template");
		clickOnElement(memberInClaimGroupHeader);
	}
	
	/* Click on 'Provider' Group header in Claim template */
	public void clickOnProviderInClaimGroupHeader() {
		testLog.lg.info("Click on 'Provider' group header");
		clickOnElement(providerInClaimGroupHeader);
	}
	
	/* Click on 'Member' Group header */
	public void clickOnMemberGroupHeader() {
		testLog.lg.info("Click on 'Member' group header");
		clickOnElement(memberGroupHeaderLocator);
	}
	
	/*Click on 'Provider' Group header*/
	public void clickOnProviderGroupHeader() {
		testLog.lg.info("Click on 'Provider' group header");
		clickOnElement(providerGroupHeaderLocator);
	}
	
	/* Verify if 'Claim' group is wrapped */
	public boolean verifyClaimGroupWrapped() {
		testLog.lg.info("Verify if 'Claim' group is wrapped");
		String attributeValue = getAttributeValue(claimWrapperLoactor, "class");
		if (attributeValue.contains("down")) {
			testLog.lg.info("'Claim' group is wrapped");
			return true;
		} else {
			testLog.lg.info("'Claim' group is unwrapped");
			return false;
		}
	}
	
	/* Verify if 'Member' group is wrapped */
	public boolean verifyMemberGroupWrapped() {
		testLog.lg.info("Verify if 'Member' group is wrapped");
		String attributeValue = getAttributeValue(memberWrapperLocator, "class");
		if (attributeValue.contains("down")) {
			testLog.lg.info("'Member' group is wrapped");
			return true;
		} else {
			testLog.lg.info("'Member' group is unwrapped");
			return false;
		}
	}
	
	/* Verify if 'Provider' group is wrapped */
	public boolean verifyProviderGroupWrapped() {
		testLog.lg.info("Verify if 'Provider' group is wrapped");
		String attributeValue = getAttributeValue(providerWrapperLocator, "class");
		if (attributeValue.contains("down")) {
			testLog.lg.info("'Provider' group is wrapped");
			return true;
		} else {
			testLog.lg.info("'Provider' group is unwrapped");
			return false;
		}
	}
		
	/* Count number of attributes */
	public int getNumOfAttributesAtTemplate() {
		testLog.lg.info("Count attributes at template");
		int fieldNum = countElements(attributeLocator);
		testLog.lg.info("Number of attributes at template is: " + fieldNum);
		return fieldNum;
	}
	
	/* Search through attributes */
	public void searchThroughAttributes(String searchValue) {
		testLog.lg.info("Search for: " + searchValue);
		putTextToElement(searchLocator, searchValue);
	}
	
	/* Count number of shown attributes after search */
	public int countFoundAttributes(String searchValue) {
		testLog.lg.info("Count found attributes with value " + searchValue);
		String XPath = searchResultXPath.replace("searchValue",searchValue);
		By searchResult = By.xpath(XPath);
		int numOfAttributes = countElements(searchResult);
		testLog.lg.info(numOfAttributes + " attributes with text " + searchValue + " found");
		return numOfAttributes;
	}
	
	/* Clear Search */
	public void clearSearch() {
		testLog.lg.info("Clear Search");
		clearElement(searchLocator);
	}
	
	
	/* Click on any attribute */
	public String clickOnAnyAttribute() {
		testLog.lg.info("Click on any attribute");
		Random random = new Random();
		List<WebElement> anyElements = getElements(attributeLocator);
		int elementNum = countElements(attributeLocator);
		int i = random.nextInt(elementNum-1);
		try {
			anyElements.get(i).click();
			String attributeName = anyElements.get(i).getText();
			testLog.lg.info("Field '" + attributeName + "' is selected");
			return attributeName;
		} catch (Exception e) {
			testLog.lg.debug("ERROR clicking on attribute '" + anyElements.get(i).getText() + "'\n" + e.getMessage());
			return null;
		}
		
	}	
}
