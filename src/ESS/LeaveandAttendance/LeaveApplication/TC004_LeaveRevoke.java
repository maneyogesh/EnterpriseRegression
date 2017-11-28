package ESS.LeaveandAttendance.LeaveApplication;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pom.ObjectPages.uiActions.HomePage;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.test.UIAction.LeaveApplication;
import pom.test.UIAction.TC002_VerifyRegistration;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC004_LeaveRevoke extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_VerifyRegistration.class.getName());
	HomePage homepage;
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	LeaveApplication la;
	BaseClass bc;
	
	@DataProvider(name="LeaveRevoke")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance.xlsx", "LeaveRevoke");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	  	driver=getDriver();
	  }

	@Test(priority=0)
	public void Login() throws Exception{
		la = new LeaveApplication(driver);
		la.Test_Login_Submitter();
	}
		
	@Test(priority=1)
	public void TargetPage() throws Exception{
		la = new LeaveApplication(driver);
		la.Test_Open_Target_Page();
	}
		
	
	@Test(priority=2,dependsOnMethods = { "TargetPage" },dataProvider="LeaveRevoke")
	public void VerifyLeave(String verifyDate,String VerifyStatus,String ActionData,String RevokeStatus) throws Exception {
		bc = new BaseClass(driver);
		la = new LeaveApplication(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		  try {
		la.Test_LeaveRevoke(verifyDate, VerifyStatus, ActionData, RevokeStatus);
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("leave revoke successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("verify Leave");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("leave not revoke", iTestCaseRow, Constant.Col_ActualResult);
			Reporter.log("login with same user those are apply leave -> open approved leave -> revoke submit -> not showing proper status under status column");
			Assert.fail("Leave status is not showing properly in status column ");
		}
	}
	
	

	
}

	
	

