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

public class LeftNavBarTest {
	private SoftAssert sa = new SoftAssert();
	public Framework work = new Framework();
	public Logging testLog = new Logging();
	public LeftNavBar leftNavBar; 
	
	@Parameters ("browser")
	@BeforeMethod
    public void before(String browser) {
    		 testLog.startLogger(browser,"LeftNavBarTest_");
    		 work.startDriver(browser);
    		 leftNavBar = new LeftNavBar (work.driver, testLog);
    }
	
	@Test
	public void verifyLeftSideControls() {
		//Verify 'Claim' Group
		leftNavBar.selectClaimMenuItem();
		//Unwrap Member and Provider Groups at Claim template
		leftNavBar.clickOnMemberInClaimGroupHeader();
		leftNavBar.clickOnProviderInClaimGroupHeader();
		sa.assertEquals(leftNavBar.getNumOfAttributesAtTemplate(), 170, "ERROR: Number of attributes at Claim template isn't 170" + testLog.appender.getLogAsString());
		leftNavBar.clickOnClaimGroupHeader();
		sa.assertTrue(leftNavBar.verifyClaimGroupWrapped(), "ERROR: Claim Group isn't wrapped" + testLog.appender.getLogAsString());
		leftNavBar.clickOnClaimGroupHeader();
		sa.assertFalse(leftNavBar.verifyClaimGroupWrapped(), "ERROR: Claim Group is wrapped" + testLog.appender.getLogAsString());
		leftNavBar.searchThroughAttributes("type");
		sa.assertEquals(leftNavBar.countFoundAttributes("type"), 8, "ERROR: Number of found attributes conatined 'type' isn't 8" + testLog.appender.getLogAsString());
		leftNavBar.clearSearch();
		
		//Verify 'Member' Group
		leftNavBar.selectMemberMenuItem();
		sa.assertEquals(leftNavBar.getNumOfAttributesAtTemplate(), 58, "ERROR: Number of attributes at Member template isn't 58" + testLog.appender.getLogAsString());
		leftNavBar.clickOnMemberGroupHeader();
		sa.assertTrue(leftNavBar.verifyMemberGroupWrapped(), "ERROR: Member Group isn't wrapped" + testLog.appender.getLogAsString());
		leftNavBar.clickOnMemberGroupHeader();
		sa.assertFalse(leftNavBar.verifyMemberGroupWrapped(), "ERROR: Member Group is wrapped" + testLog.appender.getLogAsString());
		leftNavBar.searchThroughAttributes("name");
		sa.assertEquals(leftNavBar.countFoundAttributes("name"), 6, "ERROR: Number of found attributes conatined 'name' isn't 6" + testLog.appender.getLogAsString());
		leftNavBar.clearSearch();
		
		//Verify 'Provider' Group
		leftNavBar.selectProviderMenuItem();
		sa.assertEquals(leftNavBar.getNumOfAttributesAtTemplate(), 21, "ERROR: Number of attributes at Provider template isn't 58" + testLog.appender.getLogAsString());
		leftNavBar.clickOnProviderGroupHeader();
		sa.assertTrue(leftNavBar.verifyProviderGroupWrapped(), "ERROR: Member Group isn't wrapped" + testLog.appender.getLogAsString());
		leftNavBar.clickOnProviderGroupHeader();
		sa.assertFalse(leftNavBar.verifyProviderGroupWrapped(), "ERROR: Member Group is wrapped" + testLog.appender.getLogAsString());
		leftNavBar.searchThroughAttributes("facility");
		sa.assertEquals(leftNavBar.countFoundAttributes("facility"), 3, "ERROR: Number of found attributes conatined 'facility' isn't 3" + testLog.appender.getLogAsString());
		leftNavBar.clearSearch();		     
				
		sa.assertAll();
		
	}
	
	@Parameters ("browser")
	@AfterMethod
	 public void after(String browser){
		// report status to TestLink, Save logs  	
		TLink tl = new TLink();  
		tl.setResult(this.getClass().getSimpleName().toString(), testLog.analyzeTestResult(work, sa), browser);
		   	   
		work.stopDriver();
		testLog.stopLogger();
	 }
}
