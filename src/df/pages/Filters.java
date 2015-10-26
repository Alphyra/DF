package df.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import df.pages.BasePage; 
import df.myLogger.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Filters page object. It extends PageBase class
 * It provides properties and methods to test actions for filters
 */

public class Filters extends BasePage {
	
	/* 
	 * Filters contain HTML elements that will be represented as WebElements.
	 * The locators for these elements should only be defined once.
	 */
	
	// Locator for abstract filter icon
	By filterIconLocator = By.xpath(".//*[@class='glyphicon icon-add-filter']");
	// 'Scope Filtering' label
	By scopeFilteringLabelLocator = By.xpath(".//*[@id='tabDefinition']//span[contains(text(),'Scope Filtering')]");
	// Wrapper for filter scope
	By closeFilterScopeLocator = By.xpath(".//*[@id='tabDefinition']//*[contains(@class,'menu-up')]");
	// Unwrapper for filter scope
	By openFilterScopeLocator = By.xpath(".//*[@id='tabDefinition']//*[contains(@class,'menu-down')]");
	// By filtersWrapperLocator = By.xpath(xpathExpression);
	// Abstract Filter locator at 'Definition' tab 
	By filterLocator = By.cssSelector(".form-inline");
	// Button for deleting filter
	By deleteFilterBtnLocator = By.xpath(".//button//*[contains(@class,'remove')]");
	// text and number input for filter value
	By filterInputLocator = By.xpath(".//*[@class='input-group']/input");
	// Apply filter button
	By applyfilterBtnLocator = By.xpath(".//button/*[contains(@class,'ok-circle')]");
	// Compare filter menu locator
	By compareMenuLocator = By.id("numberCompareTypesMenu");
	// 'ABOVE' item at compare menu filter
	By aboveCompareMenuItemLocator = By.xpath(".//*[@aria-labelledby='numberCompareTypesMenu']//*[text()='ABOVE']");
	// 'EQUAL' item at compare menu filter
	By equalCompareMenuItemLocator = By.xpath(".//*[@aria-labelledby='numberCompareTypesMenu']//*[text()='EQUAL']");
	// 'BELOW' item at compare menu filter
	By belowCompareMenuItemLocator = By.xpath(".//*[@aria-labelledby='numberCompareTypesMenu']//*[text()='BELOW']");
	// Comparative Filter Entry - contains selected type of Filter (ABOVE,BELOW,EQUAL)
	By compareFiterEntryLocator = By.xpath(".//*[@id='numberCompareTypesMenu']/span[1]");
	// Date Range filter menu locator
	By dateRangeMenuLocator = By.id("dateTypeMenu");
	// Calendar icon for Date range filter
	By filterCalendarLocator = By.cssSelector(".input-group-addon");
	
	
	
	// XPath for random filter icon at Left Navigation Bar, which will be selected randomly for test
	// attributeName - will be replaced by real search value
	String filterIconXPath = ".//*[contains(@class,'list-group-item pointer')]//span[text()='attributeName']/../../span[2]/span";
	// lists of filtrable attributes according to filter types
	List<String> filterText = new ArrayList<String>();
	List<String> filterComparative = new ArrayList<String>();
	List<String> filterDateRange =  new ArrayList<String>();
	
	/* Constructor: initialized and load this page properties. */ 
	public Filters(WebDriver driver, Logging testLog){
		super(driver, testLog);
		initFiltrableAttributes();
	}
	
	/* Initialization of lists, which contains filtrable attributes with corresponding filter type */
	public void initFiltrableAttributes(){
		String csvFile = "filterListWork.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
				
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null){
				// use comma as separator
				String[] filterInfo = line.split(csvSplitBy);
				switch (filterInfo[2]) {
					case "text": filterText.add(filterInfo[1]);
								break;
					case "comparative": filterComparative.add(filterInfo[1]);
								break;
					case "date range": filterDateRange.add(filterInfo[1]);
								break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try { 
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* Count number of filters */
	public int countFilters(){
		testLog.lg.info("Count number of filters");
		int numOfFilters = countElements(filterIconLocator);
		testLog.lg.info(numOfFilters + " filters found");
		return numOfFilters;
	}
	
	/* Click on any text filter */
	public void clickAnyTextFilter(){
		testLog.lg.info("Click on any text filter");
		Random random = new Random();
		int num = filterText.size();
		int i = random.nextInt(num-1);
		String XPath = filterIconXPath.replace("attributeName",filterText.get(i));
		try {
			By filterTextLocator = By.xpath(XPath);
			clickOnElement(filterTextLocator);
			testLog.lg.info("Click on filter for field '" + filterText.get(i) + "' was done");
		} catch (Exception e) {
			testLog.lg.debug("ERROR clicking on filter for field '" + filterText.get(i) + "'\n" + e.getMessage());
		}
	}
	
	/* Click on any comparative filter */
	public void clickAnyComparativeFilter(){
		testLog.lg.info("Click on any comparative filter");
		Random random = new Random();
		int num = filterText.size();
		int i = random.nextInt(num-1);
		String XPath = filterIconXPath.replace("attributeName",filterComparative.get(i));
		try {
			By filterComparativeLocator = By.xpath(XPath);
			clickOnElement(filterComparativeLocator);
			testLog.lg.info("Click on filter for attribute '" + filterComparative.get(i) + "' was done");
		} catch (Exception e) {
			testLog.lg.debug("ERROR clicking on filter for attribute '" + filterComparative.get(i) + "'\n" + e.getMessage());
		}
	}
	
	/* Click on any comparative filter */
	public void clickAnyDateRangeFilter(){
		testLog.lg.info("Click on any date range filter");
		Random random = new Random();
		int num = filterDateRange.size();
		int i = random.nextInt(num-1);
		String XPath = filterIconXPath.replace("attributeName",filterDateRange.get(i));
		try {
			By filterComparativeLocator = By.xpath(XPath);
			clickOnElement(filterComparativeLocator);
			testLog.lg.info("Click on filter for attribute '" + filterDateRange.get(i) + "' was done");
		} catch (Exception e) {
			testLog.lg.debug("ERROR clicking on filter for attribute '" + filterDateRange.get(i) + "'\n" + e.getMessage());
		}
	}
	
	/* Verify 'Scope Filtering' label  presence */
	public boolean verifyScopeFilteringLabelPresence(){
		testLog.lg.info("Verifying of 'Scope Filtering' label presence");
		if (isElementPresent(scopeFilteringLabelLocator)){
			testLog.lg.info("'Scope Filtering' label is present");
			return true;
		} else {
			testLog.lg.info("'Scope Filtering' label isn't present");
			return false;
		} 		
	}
	
	/* Verify if filter present */
	public boolean verifyFilterPresence(){
		testLog.lg.info("Verifying of Filter presence");
		if (isElementPresent(filterLocator)){
			testLog.lg.info("Filter is present");
			return true;
		} else {
			testLog.lg.debug("Filter isn't present");
			return false;
		} 		
	}
	
	/* Apply text or number. filter with value */
	public void applyFilterWithValue(String filterValue){
		testLog.lg.info("Applying of Filter");
		putTextToElement(filterInputLocator,filterValue);
		clickOnElement(applyfilterBtnLocator);
	}
	
	/* Delete filter */
	public void deleteFilter(){
		testLog.lg.info("Delete Filter");
		clickOnElement(deleteFilterBtnLocator);
	}
	
	/* Select 'ABOVE' filter for comparative menu */
	public void selectAboveComparativeFilter(){
		testLog.lg.info("Select ABOVE filter drom comparative menu");
		clickOnElement(compareMenuLocator);
		clickOnElement(aboveCompareMenuItemLocator);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}
		if (getText(compareFiterEntryLocator).equals("ABOVE")){
			testLog.lg.info("ABOVE filter condition was selected");
		} else {
			testLog.lg.info("ABOVE filter condition WASN'T selected");
		}		
	}
	
	/* Select 'EQUAL' filter for comparative menu */
	public void selectEqualComparativeFilter(){
		testLog.lg.info("Select EQUAL filter drom comparative menu");
		clickOnElement(compareMenuLocator);
		clickOnElement(equalCompareMenuItemLocator);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}		
		if (getText(compareFiterEntryLocator).equals("EQUAL")){
			testLog.lg.info("EQUAL filter condition was selected");
		} else {
			testLog.lg.info("EQUAL filter condition WASN'T selected");
		}		
	}
	
	/* Select 'BELOW' filter for comparative menu */
	public void selectBelowComparativeFilter(){
		testLog.lg.info("Select BELOW filter drom comparative menu");
		clickOnElement(compareMenuLocator);
		clickOnElement(aboveCompareMenuItemLocator);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}
		if (getText(compareFiterEntryLocator).equals("BELOW")){
			testLog.lg.info("BELOW filter condition was selected");
		} else {
			testLog.lg.info("BELOW filter condition WASN'T selected");
		}		
	}
	
	/* Close Filter Scope */
	public void closeFilterScope(){
		testLog.lg.info("Close Filter Scope");
		clickOnElement(closeFilterScopeLocator);
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(openFilterScopeLocator));
	}
	
	/* Open Filter Scope */
	public void OpenFilterScope(){
		testLog.lg.info("Open Filter Scope");
		clickOnElement(openFilterScopeLocator);
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(closeFilterScopeLocator));
	}
}
