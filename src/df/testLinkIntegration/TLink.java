package df.testLinkIntegration;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

import java.net.MalformedURLException;
import java.net.URL;

import df.projectSettings.*;

public class TLink {
    private final static String url = ProjectSettingsReader.properties.getProperty("TLUrl");
	private final static String devKey = ProjectSettingsReader.properties.getProperty("TLKey");
	private final static Integer testPlanId = Integer.parseInt(ProjectSettingsReader.properties.getProperty("TLPlanId"));
	private final static Integer buildId = Integer.parseInt(ProjectSettingsReader.properties.getProperty("TLBuildId"));
	private final static String  projectName = ProjectSettingsReader.properties.getProperty("TLProjectName");
	private final static String  suiteName = ProjectSettingsReader.properties.getProperty("TLSuiteName");
	TestLinkAPI testlinkAPIClient;
	
	/* Constructor: initialization of fields and setting coonection to TestLink */
	public TLink(){
		try {
			testlinkAPIClient = new TestLinkAPI(new URL(url), devKey);
		} catch (TestLinkAPIException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
		
	/* Get  test case id at Test Link by test case Name 
	 * Test case name at TestLink is equal to class name of appropriate test
	 * */
	public int getTestCaseId (String testCaseName) {
		try {
			int testCaseId = testlinkAPIClient.getTestCaseIDByName(testCaseName, suiteName, projectName, null);
			return testCaseId;
		} catch (TestLinkAPIException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/* Set Result to TestLink */
	public void setResult (String testCaseName, boolean result, String browser){
		try {
			ExecutionStatus status;
			if (result) {
				status = ExecutionStatus.PASSED;
			} else {
				status = ExecutionStatus.FAILED;
			}
			try {
				/* Every test case can be run at several browsers
				 * At TestLink one test is presented several times with appropriate postfix : 'chrome', 'firefox', 'ie'
				 */
				int testCaseNameAtTestLink = getTestCaseId(testCaseName + "_" + browser);
				testlinkAPIClient.setTestCaseExecutionResult(testCaseNameAtTestLink, null, testPlanId, status, buildId, null, null, true, null, null, null, null, false);
			} catch (TestLinkAPIException e) {
				e.printStackTrace();
			}
		}  catch (TestLinkAPIException e) {
			e.printStackTrace();
		   } catch (Exception e) {
				e.printStackTrace();
		   }
		
	}
}
     
