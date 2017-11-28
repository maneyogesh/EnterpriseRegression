package Module.OrganizationalSetup;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.AttributeMaster;
import pom.ObjectPages.uiActions.EntityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC023_AttributeMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC023_AttributeMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	EntityMaster em;
	AttributeMaster am;
	BaseClass bc;
	TestBase tb;
	
	@DataProvider(name="AttributeMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("AttributeMaster.xlsx", "AttributeMaster");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="AMLogin")
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
		
	@Test(priority=1,groups="AMTargetPage",dependsOnGroups = {"AMLogin"})
	public void Target_Page() throws Exception{
		am = new AttributeMaster(driver);
		am.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="AMAddNewButton",dependsOnGroups = {"AMTargetPage"})
	public void AddNew_Button(){
		am = new AttributeMaster(driver);
		am.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"AMAddNewButton"},dataProvider="AttributeMasterData")
	public void Data_Upload(String AttributeData, String SubAttributeCodeData, String SubAttributeDescriptiondata, String SubAttributeshortDescriptiondata, String SapCodedata, String GeneralLedgerCodedata, String GeneralLedgerDescriptiondata) throws Exception {
		bc = new BaseClass(driver);
		am = new AttributeMaster(driver);
		tb = new TestBase();
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		am.Test_AddNewDataUpload(AttributeData, SubAttributeCodeData, SubAttributeDescriptiondata, SubAttributeshortDescriptiondata, SapCodedata, GeneralLedgerCodedata, GeneralLedgerDescriptiondata);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully");
			 tb.log("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Attribute Master");
			WebElement CancelButton = driver.findElement(By.id("ContentPlaceHolder_btnCANCELsub"));
			bc.click(CancelButton, "Cancel Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	
	@Test(priority=4,dependsOnGroups = {"AMAddNewButton"})
	public void Edit_Record(){
		am = new AttributeMaster(driver);
		am.Test_EditRecord();
	}
	
}

	
	

