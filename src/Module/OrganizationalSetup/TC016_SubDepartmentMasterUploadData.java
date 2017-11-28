package Module.OrganizationalSetup;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EntityMaster;
import pom.ObjectPages.uiActions.SubDepartmentMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC016_SubDepartmentMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC016_SubDepartmentMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	EntityMaster em;
	SubDepartmentMaster sdm;
	BaseClass bc;
	TestBase tb;
	
	@DataProvider(name="SubDepartmentMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("SubDepartmentMaster.xlsx", "SubDepartmentMaster");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="subDeMLogin")
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
		
	@Test(priority=1,groups={"subDeMTargetPage"},dependsOnGroups = {"subDeMLogin"})
	public void Target_Page() throws Exception{
		sdm = new SubDepartmentMaster(driver);
		sdm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="subDeMAddNewButton",dependsOnGroups = {"subDeMTargetPage"})
	public void AddNew_Button(){
		sdm = new SubDepartmentMaster(driver);
		sdm.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"subDeMAddNewButton"},dataProvider="SubDepartmentMasterData")
	public void Data_Upload(String subDepartmentCodeData, String subDepartmentNameData, String subDepartmentShortNameData, String DepartmentNameDropdownData, String SubDepartmentRemarksData, String ActiveData) throws Exception {
		bc = new BaseClass(driver);
		sdm = new SubDepartmentMaster(driver);
		tb = new TestBase();
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		sdm.Test_AddNewDataUpload(subDepartmentCodeData, subDepartmentNameData, subDepartmentShortNameData, DepartmentNameDropdownData, SubDepartmentRemarksData, ActiveData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully");
			 tb.log("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Sub Department Master");
			WebElement CancelButton = driver.findElement(By.id("ContentPlaceHolder_CancelButton"));
			bc.click(CancelButton, "Cancel Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	
	@Test(priority=4,dependsOnGroups = {"subDeMAddNewButton"})
	public void Edit_Record(){
		sdm = new SubDepartmentMaster(driver);
		sdm.Test_EditRecord();
	}
	
}

	
	

