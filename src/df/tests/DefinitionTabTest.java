package df.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import df.pages.*;
import df.testLinkIntegration.TLink;
import df.myLogger.*;
import df.framework.Framework;

public class DefinitionTabTest{
	private SoftAssert sa = new SoftAssert();
	public Framework work = new Framework();
	public Logging testLog = new Logging();
	public LeftNavBar leftNavBar; 
	public DefinitionTab definitionTab;
	
	@Parameters ("browser")
	@BeforeMethod
    public void before(String browser) {
    		 testLog.startLogger(browser,"DefinitionTab_");
    		 work.startDriver(browser);
    		 leftNavBar = new LeftNavBar (work.driver, testLog);
    		 definitionTab = new DefinitionTab (work.driver, testLog);
    }
	
	@Test
	public void verifyDefinitionTabsControlds() {
		sa.assertTrue(definitionTab.verifyDefinitionTabPresence(), "ERROR: 'Definition' tab isn't present" + testLog.appender.getLogAsString());
		//Verify that all buttons are disabled
		sa.assertTrue(definitionTab.verifyStartPreviewBtnIsDisabled(), "ERROR: 'Start Preview' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyClearPreviewDataBtnIsDisabled(), "ERROR: 'Clear Preview Data' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyClearAllBtnIsDisabled(), "ERROR: 'Clear All' button isn't disabled" + testLog.appender.getLogAsString());
		//Verify text at Info Label
		sa.assertTrue(definitionTab.verifyTextAtInfoLabel("Click the 'Start Preview' button above to start streaming"), "ERROR: incorrect text at Info label" + testLog.appender.getLogAsString());
		//Select 'Claim' as Template
		leftNavBar.selectClaimMenuItem();
		//Unwrap all Groups
		leftNavBar.clickOnMemberInClaimGroupHeader();
		leftNavBar.clickOnProviderInClaimGroupHeader();
		//Select random attribute at left navigation bar
		String attributeNameAtLeftNavBar = leftNavBar.clickOnAnyAttribute();
		// Verify that attribute at 'Definition' tab is the same as selected at Left Navigation Bar
		sa.assertEquals(definitionTab.getAttributeName(), attributeNameAtLeftNavBar.substring(1), "ERROR: shown attribute isn't the same as selected" + testLog.appender.getLogAsString());
		//Verify that 
		sa.assertFalse(definitionTab.verifyStartPreviewBtnIsDisabled(), "ERROR: 'Start Preview' button is disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyClearPreviewDataBtnIsDisabled(), "ERROR: 'Clear Preview Data' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertFalse(definitionTab.verifyClearAllBtnIsDisabled(), "ERROR: 'Clear All' button is disabled" + testLog.appender.getLogAsString());
		//Start Preview
		definitionTab.clickStartPreviewButton();
		//Verify text at Info label
		sa.assertTrue(definitionTab.verifyTextAtInfoLabel("Preview started:"), "ERROR: incorrect text at Info label" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyTextAtInfoLabel(work.getCurrentTime()), "ERROR: incorrect text at Info label" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyTextAtInfoLabel("Last record received: never"), "ERROR: incorrect text at Info label" + testLog.appender.getLogAsString());
		//Verify that 'Clear All' button is disable
		sa.assertTrue(definitionTab.verifyClearAllBtnIsDisabled(), "ERROR: 'Clear All' button isn't disabled" + testLog.appender.getLogAsString());
		//Stop Preview
		definitionTab.clickStopPreview();
		//Verify that selected fields are still present
		sa.assertFalse(definitionTab.verifyDefinitionTabClear(), "ERROR: 'Definition' tab isn't clear" + testLog.appender.getLogAsString());
		//Verify buttons
		sa.assertFalse(definitionTab.verifyStartPreviewBtnIsDisabled(), "ERROR: 'Start Preview' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyClearPreviewDataBtnIsDisabled(), "ERROR: 'Clear Preview Data' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertFalse(definitionTab.verifyClearAllBtnIsDisabled(), "ERROR: 'Clear All' button is disabled" + testLog.appender.getLogAsString());
		//Clear 'Dataset Definition' tab
		definitionTab.clickClearAllButton();
		//Verify tab, buttons, info label
		sa.assertTrue(definitionTab.verifyDefinitionTabClear(), "ERROR: 'Definition' tab isn't clear" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyStartPreviewBtnIsDisabled(), "ERROR: 'Start Preview' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyClearPreviewDataBtnIsDisabled(), "ERROR: 'Clear Preview Data' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyClearAllBtnIsDisabled(), "ERROR: 'Clear All' button isn't disabled" + testLog.appender.getLogAsString());
		sa.assertTrue(definitionTab.verifyTextAtInfoLabel("Click the 'Start Preview' button above to start streaming"), "ERROR: incorrect text at Info label" + testLog.appender.getLogAsString());	
				
		sa.assertAll();
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
