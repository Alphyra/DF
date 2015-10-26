package df.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import df.pages.*;
import df.testLinkIntegration.TLink;
import df.framework.Framework;
import df.myLogger.*;

public class FiltersTest {
	private SoftAssert sa = new SoftAssert();
	public Framework work = new Framework();
	public Logging testLog = new Logging();
	public LeftNavBar leftNavBar; 
	public Filters filters;
	
	@Parameters ("browser")
	@BeforeMethod
    public void before(String browser) {
    		 testLog.startLogger(browser,"Filters_");
    		 work.startDriver(browser);
    		 leftNavBar = new LeftNavBar (work.driver, testLog);
    		 filters = new Filters (work.driver, testLog);
    }
	
	@Test
	public void verifyFiltersControlds(){
		//Select 'Claim' as Template
		leftNavBar.selectClaimMenuItem();
		//Unwrap all Groups
		leftNavBar.clickOnMemberInClaimGroupHeader();
		leftNavBar.clickOnProviderInClaimGroupHeader();
		//Verify number of filtrable fields
		sa.assertEquals(filters.countFilters(), 23, "ERROR: number of filtrable fields isn't 23" + testLog.appender.getLogAsString());
		
		//Verify text filter
		filters.clickAnyTextFilter();
		//Verify label
		sa.assertTrue(filters.verifyScopeFilteringLabelPresence(), "ERROR: 'Scope Filtering' isn't present" + testLog.appender.getLogAsString());
		//Verify filter is present at 'Definition' tab
		sa.assertTrue(filters.verifyFilterPresence(), "ERROR: Text filter isn't present at 'Definition' tab" + testLog.appender.getLogAsString());
		filters.applyFilterWithValue("Text Filter");
		filters.deleteFilter();
		//Verify that filter and 'Scope Filtering' isn't present
		sa.assertFalse(filters.verifyFilterPresence(), "ERROR: Text filter isn't deleted" + testLog.appender.getLogAsString());
		sa.assertFalse(filters.verifyScopeFilteringLabelPresence(), "ERROR: 'Scope Filter' is present" + testLog.appender.getLogAsString());
		
		//Verify Comparative filter
		filters.clickAnyComparativeFilter();
		//Verify label
		sa.assertTrue(filters.verifyScopeFilteringLabelPresence(), "ERROR: 'Scope Filtering' isn't present" + testLog.appender.getLogAsString());
		//Verify filter is present at 'Definition' tab
		sa.assertTrue(filters.verifyFilterPresence(), "ERROR: Comparative filter isn't present at 'Definition' tab" + testLog.appender.getLogAsString());
		filters.selectAboveComparativeFilter();
		filters.selectEqualComparativeFilter();
		filters.selectBelowComparativeFilter();
		filters.applyFilterWithValue("10");
		
		//Verify Scope Filtering
		filters.closeFilterScope();
		sa.assertFalse(filters.verifyFilterPresence(), "ERROR: 'Scope Filtering' is still open" + testLog.appender.getLogAsString());
		filters.OpenFilterScope();
		sa.assertTrue(filters.verifyFilterPresence(), "ERROR: 'Scope Filtering' is still close" + testLog.appender.getLogAsString());
		
		filters.deleteFilter();
		//Verify that filter and 'Scope Filtering' isn't present
		sa.assertFalse(filters.verifyFilterPresence(), "ERROR: Comparative filter isn't deleted" + testLog.appender.getLogAsString());
		sa.assertFalse(filters.verifyScopeFilteringLabelPresence(), "ERROR: 'Scope Filter' is present" + testLog.appender.getLogAsString());
		
		sa.assertAll();
		
		// Date Range filter will be covered after resolving of bug 146370
	}
	
	@Parameters ("browser")
	@AfterMethod
	 public void after(String browser) {
		// report status to TestLink, Save logs  	
		TLink tl = new TLink();  
		tl.setResult(this.getClass().getSimpleName().toString(), testLog.analyzeTestResult(work, sa), browser);
		   	   
		work.stopDriver();
		testLog.stopLogger();
	}
}
