package Module.OrganizationalSetup;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.PositionMapping;
import pom.ObjectPages.uiActions.EntityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC025_PositionMappingUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC025_PositionMappingUploadData.class.getName());
	private String sTestCaseNdme;
	private int iTestCaseRow;
	EntityMaster em;
	PositionMapping pm;
	BaseClass bc;
	TestBase tb;
	
	@DataProvider(name="PositionMappingData")
	public String[][] getTestData(){
		String[][] testRecords = getData("PositionMapping.xlsx", "PositionMapping");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="pmLogin")
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
		
	@Test(priority=1,groups="pmTargetPage",dependsOnGroups = {"pmLogin"})
	public void Target_Page() throws Exception{
		pm = new PositionMapping(driver);
		pm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="pmAddNewButton",dependsOnGroups = {"pmTargetPage"})
	public void AddNew_Button(){
		pm = new PositionMapping(driver);
		pm.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"pmAddNewButton"},dataProvider="PositionMappingData")
	public void Data_Upload(String Positiondata, String DepartmentValueData) throws Exception {
		bc = new BaseClass(driver);
		pm = new PositionMapping(driver);
		tb = new TestBase();
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		pm.Test_AddNewDataUpload(Positiondata, DepartmentValueData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			 tb.log("Record Added successfully");
			System.out.println("Record Saved Successfully.");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Position Mapping");
			WebElement CancelButton = driver.findElement(By.id("ContentPlaceHolder_btnCANCELsub"));
			bc.click(CancelButton, "Cancel Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	
	@Test(priority=4,dependsOnGroups = {"pmAddNewButton"})
	public void Edit_Record(){
		pm = new PositionMapping(driver);
		pm.Test_EditRecord();
	}
	
}

	
	

