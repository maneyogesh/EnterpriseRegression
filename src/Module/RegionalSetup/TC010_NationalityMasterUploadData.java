package Module.RegionalSetup;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EntityMaster;
import pom.ObjectPages.uiActions.NationalityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC010_NationalityMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC010_NationalityMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EntityMaster em;
	NationalityMaster nm;
	BaseClass bc;
	
	@DataProvider(name="NationalityMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("NationalityMaster.xlsx", "NationalityMaster");
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
		em = new EntityMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		em.Test_Login();
	}
		
	@Test(priority=1)
	public void Target_Page() throws Exception{
		nm = new NationalityMaster(driver);
		nm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="NMAddNewButton")
	public void AddNew_Button(){
		nm = new NationalityMaster(driver);
		nm.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"NMAddNewButton"},dataProvider="NationalityMasterData")
	public void Data_Upload(String NationalityCodeData, String NationalityNameData) throws Exception {
		bc = new BaseClass(driver);
		nm = new NationalityMaster(driver);
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		nm.Test_AddNewDataUpload(NationalityCodeData, NationalityNameData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Nationality Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	

	
}

	
	

