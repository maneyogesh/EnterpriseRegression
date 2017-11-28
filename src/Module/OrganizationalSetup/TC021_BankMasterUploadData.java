package Module.OrganizationalSetup;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.BankMaster;
import pom.ObjectPages.uiActions.EntityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC021_BankMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC021_BankMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	EntityMaster em;
	BankMaster bm;
	BaseClass bc;
	TestBase tb;
	
	@DataProvider(name="BankMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("BankMaster.xlsx", "BankMaster");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="BMLogin")
	public void Login() throws Exception{
		em = new EntityMaster(driver);
		
		sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		em.Test_Login();
	}
		
	@Test(priority=1,groups="BMTargetPage",dependsOnGroups = {"BMLogin"})
	public void Target_Page() throws Exception{
		bm = new BankMaster(driver);
		bm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="BMAddNewButton",dependsOnGroups = {"BMTargetPage"})
	public void AddNew_Button(){
		bm = new BankMaster(driver);
		bm.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"BMAddNewButton"},dataProvider="BankMasterData")
	public void Data_Upload(String BankCodeData, String BankNameData, String BankShortNameData, String BranchNamedata, String BranchAddressData, String TelNoData, String FaxNoData, String BranchEmailData, String BranchRemarkData, String AccountNoDigitData, String ActiveData) throws Exception {
		bc = new BaseClass(driver);
		bm = new BankMaster(driver);
		tb = new TestBase();
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		bm.Test_AddNewDataUpload(BankCodeData, BankNameData, BankShortNameData, BranchNamedata, BranchAddressData, TelNoData, FaxNoData, BranchEmailData, BranchRemarkData, AccountNoDigitData, ActiveData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			 tb.log("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Bank Master");
			WebElement CancelButton = driver.findElement(By.id("ContentPlaceHolder_CancelButton"));
			bc.click(CancelButton, "Cancel Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
		
	@Test(priority=4,dependsOnGroups = {"BMAddNewButton"})
	public void Edit_Record(){
		bm = new BankMaster(driver);
		bm.Test_EditRecord();
	}
	
}

	
	
