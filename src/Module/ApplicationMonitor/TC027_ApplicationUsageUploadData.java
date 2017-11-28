package Module.ApplicationMonitor;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.ApplicationUsage;
import pom.ObjectPages.uiActions.EntityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC027_ApplicationUsageUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC027_ApplicationUsageUploadData.class.getName());
	private String sTestCaseNdme;
	private int iTestCaseRow;
	EntityMaster em;
	ApplicationUsage au;
	BaseClass bc;
	TestBase tb;
	
	@DataProvider(name="ApplicationUsageData")
	public String[][] getTestData(){
		String[][] testRecords = getData("ApplicationUsage.xlsx", "StatusType");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="pgmLogin")
	public void Login() throws Exception{
		em = new EntityMaster(driver);
		
		sTestCaseNdme = this.toString();
	  	sTestCaseNdme = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseNdme,Constant.Col_TestCaseName);
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		em.Test_Login();
	}
		
	@Test(priority=1,groups="pgmTargetPage",dependsOnGroups = {"pgmLogin"})
	public void Target_Page() throws Exception{
		au = new ApplicationUsage(driver);
		au.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="pgmAddNewStatusTypeButton",dependsOnGroups = {"pgmTargetPage"})
	public void AddNew_Button(){
		au = new ApplicationUsage(driver);
		au.Test_AddNew_StatusType_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"pgmAddNewStatusTypeButton"},dataProvider="ApplicationUsageData")
	public void Data_Upload(String TypeDescriptionData, String ColumnNameData, String Querydata, String ActiveData) throws Exception {
		bc = new BaseClass(driver);
		au = new ApplicationUsage(driver);
		tb = new TestBase();
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		au.Test_AddNewDataUpload(TypeDescriptionData, ColumnNameData, Querydata, ActiveData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			 tb.log("Record Added successfully");
			System.out.println("Record Saved Successfully.");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Application Usage");
			WebElement CancelButton = driver.findElement(By.id("ContentPlaceHolder_CancelButton"));
			bc.click(CancelButton, "Cancel Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Data saved Successfully] But Found : ["+AlertMsg+"]");
		}
	}
	
	
	@Test(priority=4,dependsOnGroups = {"pgmAddNewStatusTypeButton"})
	public void Edit_Record(){
		au = new ApplicationUsage(driver);
		au.Test_EditRecord();
		
	}
	
}

	
	

